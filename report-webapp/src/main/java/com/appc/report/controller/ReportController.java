package com.appc.report.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/echart")
public class ReportController {

    @RequestMapping(value = {"/echarts{id}"}, method = RequestMethod.GET)
    public ModelAndView echarts(@PathVariable Integer id) {
        ModelAndView mv = new ModelAndView("echart/echarts" + id);//指定视图
        //向视图中添加所要展示或使用的内容，将在页面中使用
        return mv;
    }


}