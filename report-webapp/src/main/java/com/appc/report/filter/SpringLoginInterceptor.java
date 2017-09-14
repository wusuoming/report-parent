package com.appc.report.filter;

import basic.common.core.exception.BaseException;
import com.appc.report.common.ReportConstants;
import com.appc.report.model.AdminUser;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

public class SpringLoginInterceptor implements MethodInterceptor {

    @Autowired
    private HttpServletRequest request;

    @Value("${uncheck_urls}")
    private String uncheck_urls;

    @Value("${login_url}")
    private String login_url;

    public Object invoke(MethodInvocation invocation) throws Throwable {
        HttpSession session = request.getSession();

        AdminUser user = (AdminUser) session.getAttribute(ReportConstants.SESSION_KEY);

        // 登陆页面无需过滤
        if (checkRequestMatcher(uncheck_urls.split(";"))) {
            return invocation.proceed();
        } else {

            // 判断如果没有取到员工信息,就跳转到登陆页面
            if (user == null) {
                Method method = invocation.getMethod();
                Class<?> classz = method.getDeclaringClass();
                Controller controller = classz.getAnnotation(Controller.class);
                if (controller != null && method.getAnnotation(ResponseBody.class) == null) {
                    return new ModelAndView("redirect:" + login_url);
                } else {
                    throw new BaseException("010006");
                }
            } else {
                return invocation.proceed();
            }
        }

    }

    private boolean checkRequestMatcher(String[] uncheck_urlSplits) {
        RequestMatcher requestMatcher = null;
        for (String uncheck_url : uncheck_urlSplits) {
            if (StringUtils.isEmpty(uncheck_url)) {
                continue;
            } else {
                try {
                    requestMatcher = new AntPathRequestMatcher(uncheck_url, request.getMethod(), true);
                } catch (Exception e) {
                    continue;
                }

                if (requestMatcher.matches(request)) {
                    return true;
                }
            }
        }
        return false;
    }
}