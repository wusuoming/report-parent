package com.appc.report.controller;

import basic.common.core.utils.NameUtils;
import com.appc.framework.mybatis.executor.criteria.Criteria;
import com.appc.framework.mybatis.executor.criteria.EntityCriteria;
import com.appc.report.dto.PageDto;
import com.appc.report.model.AdminUser;
import com.appc.report.model.Role;
import com.appc.report.model.Rule;
import com.appc.report.model.RuleCate;
import com.appc.report.service.AdminUserService;
import com.appc.report.service.RoleService;
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
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private RuleService ruleService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private RuleCateService ruleCateService;

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public ModelAndView user(@RequestParam(required = false) Long id) {
        ModelAndView mv = new ModelAndView("admin/admin-user");
        mv.addObject("adminUser", adminUserService.getById(id));
        mv.addObject("roles", roleService.getEntityList());
        return mv;
    }

    @RequestMapping(value = "rule-cate", method = RequestMethod.GET)
    public ModelAndView cate() {
        ModelAndView mv = new ModelAndView("admin/admin-rule-cate");
        return mv;
    }

    @RequestMapping(value = "user-list", method = RequestMethod.GET)
    public ModelAndView userList() {
        ModelAndView mv = new ModelAndView("admin/admin-user-list");
        return mv;
    }

    @RequestMapping(value = "role-list", method = RequestMethod.GET)
    public ModelAndView roleList() {
        ModelAndView mv = new ModelAndView("admin/admin-role-list");
        return mv;
    }

    @RequestMapping(value = "role", method = RequestMethod.GET)
    public ModelAndView role(@RequestParam(required = false) Long id) {
        ModelAndView mv = new ModelAndView("admin/admin-role");
        mv.addObject("role", roleService.getById(id));
        mv.addObject("ruleCates", ruleCateService.getEntityList());
        return mv;
    }

    @RequestMapping(value = "role", method = RequestMethod.POST)
    public ModelAndView rolePost(Role role) {
        ModelAndView mv = new ModelAndView("admin/admin-role");
        roleService.save(role);

        mv.addObject("success", true);
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

    @RequestMapping(value = "user", method = RequestMethod.POST)
    public ModelAndView userPost(AdminUser user) {
        ModelAndView mv = new ModelAndView("admin/admin-user");
        adminUserService.save(user);
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
    public PageDto queryRule(@RequestParam int page,
                             @RequestParam int limit,
                             @RequestParam(required = false) String ruleName,
                             @RequestParam(required = false) Long ruleType,
                             @RequestParam(required = false) Long ruleCate,
                             @RequestParam(required = false) String sort,
                             @RequestParam(required = false) String order) {
        Sort sortObj = null;
        if (!StringUtils.isEmpty(order)) {
            if (!StringUtils.isEmpty(sort)) {
                sort = NameUtils.toUnderlineName(sort);
            }
            sortObj = new Sort(new Sort.Order(Sort.Direction.fromString(order), sort));
        }
        Pageable pageable = new PageRequest(page - 1, limit, sortObj);
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

    @RequestMapping(value = "ruleCate", method = RequestMethod.DELETE)
    @ResponseBody
    public void ruleCateDelete(@RequestParam Long... ids) {
        for (Long id : ids) {
            ruleCateService.deleteById(id);
        }
    }

    @RequestMapping(value = "ruleCate", method = RequestMethod.PUT)
    @ResponseBody
    public void ruleCateDelete(@RequestParam(required = false) Long id, @RequestParam String name) {
        RuleCate ruleCate = new RuleCate();
        ruleCate.setCateName(name);
        if (id != null) {
            ruleCate.setCateId(id);
            ruleCateService.updateById(ruleCate);
        } else {
            ruleCate.setCreateTime(new Date());
            ruleCateService.insert(ruleCate);
        }
    }

    @RequestMapping(value = "queryRuleCate", method = RequestMethod.GET)
    @ResponseBody
    public PageDto queryRuleCate(@RequestParam(required = false) String cateName) {
        Criteria criteria = EntityCriteria.build();
        if (!StringUtils.isEmpty(cateName)) {
            criteria.like("cate_name", cateName);
        }
        List<RuleCate> rules = ruleCateService.getEntityList(criteria);
        return PageDto.create((long) rules.size(), rules);
    }

    @RequestMapping(value = "queryRole", method = RequestMethod.GET)
    @ResponseBody
    public PageDto queryRole(@RequestParam(required = false) String roleName, String status) {
        Criteria criteria = EntityCriteria.build();
        if (!StringUtils.isEmpty(roleName)) {
            criteria.like("role_name", roleName);
        }
        if (!StringUtils.isEmpty(status)) {
            criteria.eq("status", status);
        }
        List<Role> roles = roleService.getEntityList(criteria);
        return PageDto.create((long) roles.size(), roles);
    }

    @RequestMapping(value = "queryAdminUser", method = RequestMethod.GET)
    @ResponseBody
    public PageDto queryAdminUser(@RequestParam(required = false) String username,
                                  @RequestParam(required = false) String nikeName,
                                  @RequestParam(required = false) String phone,
                                  @RequestParam(required = false) String email,
                                  @RequestParam(required = false) String status) {
        Criteria criteria = EntityCriteria.build().ne("status", "-1");
        if (!StringUtils.isEmpty(nikeName)) {
            criteria.like("nike_name", nikeName);
        }
        if (!StringUtils.isEmpty(phone)) {
            criteria.eq("phone", phone);
        }
        if (!StringUtils.isEmpty(email)) {
            criteria.eq("email", email);
        }
        if (!StringUtils.isEmpty(username)) {
            criteria.eq("username", username);
        }
        if (!StringUtils.isEmpty(status)) {
            criteria.eq("status", status);
        }
        List<AdminUser> users = adminUserService.getEntityList(criteria);

        return PageDto.create((long) users.size(), users);
    }

    @RequestMapping(value = "role", method = RequestMethod.DELETE)
    @ResponseBody
    public void roleDelete(@RequestParam Long... ids) {
        roleService.delete(ids);

    }

    @RequestMapping(value = "stopAdminUser", method = RequestMethod.PUT)
    @ResponseBody
    public void stopAdminUser(@RequestParam Long id, @RequestParam String status) {
        AdminUser adminUser = new AdminUser();
        adminUser.setId(id);
        adminUser.setStatus(status);
        adminUserService.updateById(adminUser);
    }

    @RequestMapping(value = "user", method = RequestMethod.DELETE)
    @ResponseBody
    public void userDelete(@RequestParam Long... ids) {
        for (Long id : ids) {
            AdminUser adminUser = new AdminUser();
            adminUser.setId(id);
            adminUser.setStatus("-1");
            adminUserService.updateById(adminUser);
        }
    }

    @RequestMapping(value = "stopRole", method = RequestMethod.PUT)
    @ResponseBody
    public void stopRole(@RequestParam Long id, @RequestParam String status) {
        Role role = new Role();
        role.setRoleId(id);
        role.setStatus(status);
        roleService.updateById(role);
    }


}