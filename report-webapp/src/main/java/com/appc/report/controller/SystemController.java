package com.appc.report.controller;

import basic.common.core.utils.MD5Util;
import basic.common.core.utils.SpringUtils;
import com.appc.framework.mybatis.executor.criteria.EntityCriteria;
import com.appc.report.common.ReportConstants;
import com.appc.report.model.AdminUser;
import com.appc.report.model.Rule;
import com.appc.report.model.RuleCate;
import com.appc.report.service.AdminUserService;
import com.appc.report.service.RuleCateService;
import com.appc.report.service.RuleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
public class SystemController {

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private RuleService ruleService;
    @Autowired
    private RuleCateService ruleCateService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("login");//指定视图
        //向视图中添加所要展示或使用的内容，将在页面中使用
        return mv;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout() {
        request.getSession().invalidate();
        ModelAndView mv = new ModelAndView("redirect:login.html");//指定视图
        //向视图中添加所要展示或使用的内容，将在页面中使用
        return mv;
    }

    @ApiOperation(ReportConstants.LOGIN)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loginPost(@RequestParam String username,
                                  @RequestParam String password,
                                  HttpSession session) {
        ModelAndView mv = new ModelAndView("login");//指定视图
        AdminUser user = adminUserService.getEntity(EntityCriteria.build().eq("username", username));
        if (user == null) {
            mv.addObject("error_message", SpringUtils.getLocalMessage("010002"));
        } else if (!user.getPassword().equals(MD5Util.getMD5String(password))) {
            mv.addObject("error_message", SpringUtils.getLocalMessage("010003"));
        } else if ("1".equals(user.getStatus())) {
            mv.addObject("error_message", SpringUtils.getLocalMessage("010017"));
        } else {
            session.setAttribute(ReportConstants.SESSION_KEY, user);
            List<Rule> menuList = null;
            if ("admin".equals(username)) {
                menuList = ruleService.getEntityList();
            } else {
                menuList = ruleService.getMenuList(user.getId());
            }
            List<RuleCate> ruleCates = new ArrayList<>();
            for (Rule rule : menuList) {
                RuleCate parent = null;
                for (RuleCate ruleCate : ruleCates) {
                    if (rule.getRuleCate().equals(ruleCate.getCateId())) {
                        parent = ruleCate;
                        break;
                    }
                }
                if (parent == null) {
                    parent = ruleCateService.getById(rule.getRuleCate());
                    parent.setRules(new ArrayList<>());
                    ruleCates.add(parent);
                }
                parent.getRules().add(rule);

            }
            session.setAttribute(ReportConstants.SESSION_MENU, ruleCates);

            mv = new ModelAndView("redirect:index.html");//指定视图
        }
        return mv;
    }

    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public ModelAndView password() {
        ModelAndView mv = new ModelAndView("password");//指定视图
        //向视图中添加所要展示或使用的内容，将在页面中使用
        return mv;
    }

    @ApiOperation("修改密码")
    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public ModelAndView passwordPost(@RequestParam String oldpass,
                                     @RequestParam String password,
                                     @SessionAttribute("user") AdminUser sessionUser) {
        ModelAndView mv = new ModelAndView("password");//指定视图
        AdminUser user = adminUserService.getById(sessionUser.getId());
        if (user == null) {
            mv.addObject("error_message", SpringUtils.getLocalMessage("010002"));
        } else if (!user.getPassword().equals(MD5Util.getMD5String(oldpass))) {
            mv.addObject("error_message", SpringUtils.getLocalMessage("010015"));
        } else {
            user.setPassword(MD5Util.getMD5String(password));
            adminUserService.updateById(user);
            mv.addObject("success", true);

        }
        return mv;
    }

}