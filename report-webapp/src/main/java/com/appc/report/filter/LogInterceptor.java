package com.appc.report.filter;

import basic.common.core.exception.BaseException;
import basic.common.core.id.IdUtil;
import basic.common.core.utils.IPUtils;
import basic.common.core.utils.SpringUtils;
import com.alibaba.druid.support.json.JSONUtils;
import com.appc.report.common.ReportConstants;
import com.appc.report.model.AdminUser;
import com.appc.report.model.SystemLog;
import com.appc.report.service.SystemLogService;
import io.swagger.annotations.ApiOperation;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 日志拦截器
 *
 * @author panda
 * @version 2016年4月15日 上午9:38:02
 */
public class LogInterceptor implements MethodInterceptor {

    @Autowired
    private HttpServletRequest request;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private SystemLogService systemLogService;

    public Object invoke(MethodInvocation invocation) throws Throwable {
        Long startTime = System.currentTimeMillis();
        Long logId = IdUtil.getId();
        Method method = invocation.getMethod();
        ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
        if (apiOperation != null) {
            insertSystemLog(invocation.getArguments(), request, method, logId);
            Object result = null;
            try {
                result = invocation.proceed();
            } catch (Exception e) {
                updateSystemLog(logId, e, result, System.currentTimeMillis() - startTime);
                throw e;
            }
            updateSystemLog(logId, null, result, System.currentTimeMillis() - startTime);
            return result;
        } else {
            return invocation.proceed();
        }

    }

    /**
     * TODO 这里用一句话描述这个方法的作用
     *
     * @param logId
     * @param e
     * @param result
     * @param runTime
     */
    private void updateSystemLog(final Long logId, final Exception e, final Object result, final Long runTime) {
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                SystemLog systemLog = systemLogService.getById(logId);
                if (systemLog != null) {
                    systemLog.setResponseTime(runTime);
                    if (e != null) {
                        if (e instanceof BaseException) {
                            Map<String, String> resultException = new HashMap<>();
                            String code = ((BaseException) e).getCode();
                            resultException.put("code", code);
                            try {
                                resultException.put("message", SpringUtils.getLocalMessage(code));
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                            systemLog.setResponseResult(JSONUtils.toJSONString(resultException));
                        } else {
                            systemLog.setResponseException(e.getMessage());
                        }
                    } else {
                        systemLog.setResponseResult(JSONUtils.toJSONString(result));
                    }
                    systemLogService.updateById(systemLog);
                } else {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    updateSystemLog(logId, e, result, runTime);
                }

            }
        });

    }

    public void insertSystemLog(final Object[] arguments, final HttpServletRequest request, final Method method,
                                final Long logId) {
        final String url = request.getRequestURL().toString();
        HttpSession session = request.getSession();
        final AdminUser user = (AdminUser) session.getAttribute(ReportConstants.SESSION_KEY);

        final String ip = IPUtils.getIpAddr(request);
        taskExecutor.execute(new Runnable() {

            @Override
            public void run() {
                ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
                SystemLog systemLog = new SystemLog();
                systemLog.setLogId(logId);
                systemLog.setRequestIp(ip);
                systemLog.setRequestUrl(url);
                systemLog.setRequestDate(new Date());
                systemLog.setMethodName(apiOperation.value());

                if (user != null)
                    systemLog.setRequestUser(user.getUsername());
                if (arguments != null && arguments.length > 0 && !method.getName().equals("login")
                        && !method.getName().toLowerCase().contains("password"))
                    try {
                        systemLog.setRequestArguments(JSONUtils.toJSONString(arguments));
                    } catch (Exception e) {
                        e.getMessage();
                    }

                logger.info("uri: {}", url);
                logger.info("method: {}", method);
                systemLogService.insert(systemLog);

            }
        });

    }

}