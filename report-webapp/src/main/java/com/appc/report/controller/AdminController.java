package com.appc.report.controller;

import com.appc.framework.mybatis.executor.criteria.Criteria;
import com.appc.framework.mybatis.executor.criteria.EntityCriteria;
import com.appc.report.dto.PageDto;
import com.appc.report.model.Rule;
import com.appc.report.service.RuleCateService;
import com.appc.report.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private RuleService ruleService;
    @Autowired
    private RuleCateService ruleCateService;

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView add() {
        ModelAndView mv = new ModelAndView("admin/admin-add");
        return mv;
    }

    @RequestMapping(value = "cate", method = RequestMethod.GET)
    public ModelAndView cate() {
        ModelAndView mv = new ModelAndView("admin/admin-cate");
        return mv;
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public ModelAndView edit() {
        ModelAndView mv = new ModelAndView("admin/admin-edit");
        return mv;
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("admin/admin-list");
        return mv;
    }

    @RequestMapping(value = "role", method = RequestMethod.GET)
    public ModelAndView role() {
        ModelAndView mv = new ModelAndView("admin/admin-role");
        return mv;
    }

    @RequestMapping(value = "roleAdd", method = RequestMethod.GET)
    public ModelAndView roleAdd() {
        ModelAndView mv = new ModelAndView("admin/role-add");
        return mv;
    }

    @RequestMapping(value = "rule", method = RequestMethod.POST)
    public ModelAndView rulePost(Rule rule) {
        ModelAndView mv = new ModelAndView("admin/admin-rule");
        if (rule.getRuleId() != null) {
            ruleService.updateById(rule);
        } else {
            rule.setCreateTime(new Date());
            ruleService.insert(rule);
        }
        mv.addObject("success", true);
        return mv;
    }

    @RequestMapping(value = "rule", method = RequestMethod.DELETE)
    @ResponseBody
    public void ruleDelete(@RequestParam Long... ids) {
        for (Long id : ids) {
            ruleService.deleteById(id);
        }
    }

    @RequestMapping(value = "rule", method = RequestMethod.GET)
    public ModelAndView rule(@RequestParam(required = false) Long id) {
        ModelAndView mv = new ModelAndView("admin/admin-rule");
        mv.addObject("ruleCates", ruleCateService.getEntityList());
        if (id != null) mv.addObject("rule", ruleService.getById(id));
        return mv;
    }


    @RequestMapping(value = "rule-list", method = RequestMethod.GET)
    public ModelAndView ruleList() {
        ModelAndView mv = new ModelAndView("admin/admin-rule-list");
        mv.addObject("ruleCates", ruleCateService.getEntityList());
        return mv;
    }

    @RequestMapping(value = "queryRule", method = RequestMethod.GET)
    @ResponseBody
    public PageDto queryRule(@RequestParam int page, @RequestParam int limit, @RequestParam(required = false) String ruleName, @RequestParam(required = false) Long ruleType, @RequestParam(required = false) Long ruleCate) {
        Sort sortObj = new Sort(new Sort.Order(Sort.Direction.DESC, "pay_time"));
        Pageable pageable = new PageRequest(page - 1, limit, null);
        Criteria criteria = EntityCriteria.build();
        if (!StringUtils.isEmpty(ruleName)) {
            criteria.like("rule_name", ruleName);
        }
        if (ruleType != null) {
            criteria.eq("rule_type", ruleType);
        }
        if (ruleCate != null) {
            criteria.eq("rule_cate", ruleCate);
        }
        Page<Rule> rules = ruleService.getEntityListForPage(criteria, pageable);
        return PageDto.create(rules.getTotalElements(), rules.getContent());
    }

}