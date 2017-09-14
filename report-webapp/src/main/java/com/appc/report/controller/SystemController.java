package com.appc.report.controller;

import basic.common.core.utils.MD5Util;
import basic.common.core.utils.SpringUtils;
import com.appc.framework.mybatis.executor.criteria.EntityCriteria;
import com.appc.report.common.ReportConstants;
import com.appc.report.model.AdminUser;
import com.appc.report.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@Controller
public class SystemController {

    @Autowired
    private AdminUserService adminUserService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("login");//指定视图
        //向视图中添加所要展示或使用的内容，将在页面中使用
        return mv;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loginPost(@RequestParam String username,
                                  @RequestParam String password,
                                  HttpSession session) {
        ModelAndView mv = new ModelAndView("login");//指定视图
        AdminUser user = adminUserService.getEntity(EntityCriteria.build().eq("username", username));
        if (user == null) {
            mv.addObject("error_message", SpringUtils.getLocalMessage("210002"));
        } else if (!user.getPassword().equals(MD5Util.getMD5String(password))) {
            mv.addObject("error_message", SpringUtils.getLocalMessage("210003"));
        } else {
            session.setAttribute(ReportConstants.SESSION_KEY, user);
        }
        return mv;
    }

    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public ModelAndView password() {
        ModelAndView mv = new ModelAndView("password");//指定视图
        //向视图中添加所要展示或使用的内容，将在页面中使用
        return mv;
    }
}