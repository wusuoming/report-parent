package com.appc.report.controller;

import basic.common.core.utils.NameUtils;
import com.alibaba.druid.pool.DruidDataSource;
import com.appc.framework.mybatis.executor.criteria.Criteria;
import com.appc.framework.mybatis.executor.criteria.EntityCriteria;
import com.appc.report.common.enums.DataSourseType;
import com.appc.report.common.enums.EncodingType;
import com.appc.report.dto.PageDto;
import com.appc.report.model.DataSource;
import com.appc.report.service.DataSourceService;
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

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Controller
@RequestMapping("/data")
public class DataController {

    @Autowired
    private DataSourceService dataSourceService;

    @Autowired
    private List<DruidDataSource> dataSources;


    @RequestMapping(value = "source", method = RequestMethod.GET)
    public ModelAndView source(@RequestParam(required = false) Long id) {
        ModelAndView mv = new ModelAndView("data/data-source");
        if (id != null) mv.addObject("source", dataSourceService.getById(id));
        mv.addObject("dataSourseTypes", DataSourseType.values());
        mv.addObject("encodingTypes", EncodingType.values());

        return mv;
    }

    @RequestMapping(value = "testDataSource", method = RequestMethod.POST)
    @ResponseBody
    public boolean testDataSource(DataSource dataSource) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DriverManager.getConnection(Objects.requireNonNull(DataSourseType.getTypeByCode(dataSource.getSourceType())).getJdbcUrlHelper().toJdbcUrl(dataSource), dataSource.getDataUsername(), dataSource.getDataPassword());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "importLocalDataSource", method = RequestMethod.GET)
    @ResponseBody
    public void importLocalDataSource() {
        List<DataSource> dataSourceList = new ArrayList<>();
        for (DruidDataSource druidDataSource : dataSources) {
            DataSource dataSource = new DataSource();
            dataSource.setDataUsername(druidDataSource.getUsername());
            dataSource.setDataPassword(druidDataSource.getPassword());
            try {
                Connection connection = druidDataSource.getConnection();
                String sourceType = connection.getMetaData().getDatabaseProductName();
                DataSourseType dbType = DataSourseType.getTypeByCode(sourceType);
                dataSource.setSourceType(sourceType);
                assert dbType != null;
                dbType.getJdbcUrlHelper().loadUrl(druidDataSource.getUrl(), dataSource);
                dataSource.setSourceName("当前数据源");
                dataSource.setCreateTime(new Date());
                dataSourceList.add(dataSource);
                druidDataSource.discardConnection(connection);
                druidDataSource.removeAbandoned();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        dataSourceService.insertBatch(dataSourceList);
    }


    @RequestMapping(value = "source", method = RequestMethod.DELETE)
    @ResponseBody
    public void sourceDelete(@RequestParam Long... ids) {
        for (Long id : ids) {
            dataSourceService.deleteById(id);
        }
    }


    @RequestMapping(value = "source", method = RequestMethod.POST)
    public ModelAndView sourcePost(DataSource dataSource) {
        ModelAndView mv = new ModelAndView("data/data-source");
        if (dataSource.getSourceId() != null) {
            dataSourceService.updateById(dataSource);
        } else {
            dataSource.setCreateTime(new Date());
            dataSourceService.insert(dataSource);
        }

        mv.addObject("success", true);
        return mv;
    }

    @RequestMapping(value = "source-list", method = RequestMethod.GET)
    public ModelAndView sourceList() {
        ModelAndView mv = new ModelAndView("data/data-source-list");
        mv.addObject("dataSourseTypes", DataSourseType.values());
        return mv;
    }

    @RequestMapping(value = "querySource", method = RequestMethod.GET)
    @ResponseBody
    public PageDto querySource(@RequestParam int page,
                               @RequestParam int limit,
                               @RequestParam(required = false) String sourceName,
                               @RequestParam(required = false) String sourceType,
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
        if (!StringUtils.isEmpty(sourceName)) {
            criteria.like("source_name", sourceName);
        }
        if (!StringUtils.isEmpty(sourceType)) {
            criteria.eq("source_type", sourceType);
        }

        Page<DataSource> rules = dataSourceService.getEntityListForPage(criteria, pageable);
        return PageDto.create(rules.getTotalElements(), rules.getContent());
    }

}