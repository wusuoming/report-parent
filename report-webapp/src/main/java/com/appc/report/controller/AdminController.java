package com.appc.report.controller;

import com.appc.framework.mybatis.executor.criteria.EntityCriteria;
import com.appc.report.dto.PageDto;
import com.appc.report.model.Rule;
import com.appc.report.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private RuleService ruleService;

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

    @RequestMapping(value = "ruleList", method = RequestMethod.GET)
    @ResponseBody
    public PageDto ruleList(@RequestParam int page, @RequestParam int limit) {
        Sort sortObj = new Sort(new Sort.Order(Sort.Direction.DESC, "pay_time"));
        Pageable pageable = new PageRequest(page - 1, limit, null);
        Page<Rule> rules = ruleService.getEntityListForPage(EntityCriteria.build(), pageable);
        return PageDto.create(rules.getTotalElements(), rules.getContent());
    }

}