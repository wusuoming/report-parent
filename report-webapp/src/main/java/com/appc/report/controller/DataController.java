package com.appc.report.controller;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/data")
public class DataController {

    @Autowired
    private List<DruidDataSource> dataSources;

    @RequestMapping(value = {"/local"}, method = RequestMethod.GET)
    @ResponseBody
    public List<DruidDataSource> local() {
        for (DruidDataSource dataSource : dataSources) {
            
        }
        return dataSources;
    }


}