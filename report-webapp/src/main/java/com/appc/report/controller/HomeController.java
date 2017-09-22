package com.appc.report.controller;

import com.appc.report.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {
    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping(value = {"/home", "/index", ""}, method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");//指定视图
        return mv;
    }

    @RequestMapping(value = {"/welcome"}, method = RequestMethod.GET)
    public ModelAndView welcome() {
        ModelAndView mv = new ModelAndView("welcome");//指定视图
        mv.addObject("informationStatistics", statisticsService.informationStatistics());
        return mv;
    }

    @RequestMapping(value = {"/error"}, method = RequestMethod.GET)
    public ModelAndView error() {
        ModelAndView mv = new ModelAndView("error/404");//指定视图
        return mv;
    }
}