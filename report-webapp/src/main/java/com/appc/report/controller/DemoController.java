package com.appc.report.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/demo")
public class DemoController {
    @RequestMapping(value = "table", method = RequestMethod.GET)
    public ModelAndView source(@RequestParam(required = false) Long id) {
        ModelAndView mv = new ModelAndView("demo/table");
        return mv;
    }
}
