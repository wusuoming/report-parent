package com.appc.report.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {

    @RequestMapping(value = {"/home", "/index", ""}, method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");//指定视图
        //向视图中添加所要展示或使用的内容，将在页面中使用
        return mv;
    }

    @RequestMapping(value = {"/welcome"}, method = RequestMethod.GET)
    public ModelAndView welcome() {
        ModelAndView mv = new ModelAndView("welcome");//指定视图
        //向视图中添加所要展示或使用的内容，将在页面中使用
        return mv;
    }
}