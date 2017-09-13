package com.appc.report.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView add() {
        ModelAndView mv = new ModelAndView("admin/admin-add");//指定视图
        return mv;
    }

    @RequestMapping(value = "cate", method = RequestMethod.GET)
    public ModelAndView cate() {
        ModelAndView mv = new ModelAndView("admin/admin-cate");//指定视图
        return mv;
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public ModelAndView edit() {
        ModelAndView mv = new ModelAndView("admin/admin-edit");//指定视图
        return mv;
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("admin/admin-list");//指定视图
        return mv;
    }

    @RequestMapping(value = "role", method = RequestMethod.GET)
    public ModelAndView role() {
        ModelAndView mv = new ModelAndView("admin/admin-role");//指定视图
        return mv;
    }
    @RequestMapping(value = "roleAdd", method = RequestMethod.GET)
    public ModelAndView roleAdd() {
        ModelAndView mv = new ModelAndView("admin/role-add");//指定视图
        return mv;
    }
    @RequestMapping(value = "rule", method = RequestMethod.GET)
    public ModelAndView rule() {
        ModelAndView mv = new ModelAndView("admin/admin-rule");//指定视图
        return mv;
    }
}