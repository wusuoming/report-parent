package com.appc.report.controller;

import basic.common.core.exception.BaseException;
import basic.common.core.utils.NameUtils;
import com.alibaba.druid.pool.DruidDataSource;
import com.appc.framework.mybatis.executor.criteria.Criteria;
import com.appc.framework.mybatis.executor.criteria.EntityCriteria;
import com.appc.framework.mybatis.route.DBContextHolder;
import com.appc.report.common.enums.CollectionType;
import com.appc.report.common.enums.DataSourseType;
import com.appc.report.common.enums.EncodingType;
import com.appc.report.common.helper.JdbcUrlHelper;
import com.appc.report.dto.PageDto;
import com.appc.report.model.DataCollection;
import com.appc.report.model.DataSource;
import com.appc.report.service.DataCollectionService;
import com.appc.report.service.DataSourceService;
import com.appc.report.service.DynamicDataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/data")
public class DataController {

    @Autowired
    private DataSourceService dataSourceService;
    @Autowired
    private DataCollectionService dataCollectionService;
    @Autowired
    private List<DruidDataSource> dataSources;

    @Autowired
    private DynamicDataSourceService dynamicDataSourceService;


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
        if (!checkDataSource(dataSource)) {
            return false;
        } else {
            try {
                JdbcUrlHelper jdbcUrlHelper = DataSourseType.getTypeByCode(dataSource.getSourceType()).getJdbcUrlHelper();
                Class.forName(DataSourseType.getTypeByCode(dataSource.getSourceType()).getJdbcDriver());
                DriverManager.getConnection(jdbcUrlHelper.toJdbcUrl(dataSource), dataSource.getDataUsername(), dataSource.getDataPassword());
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
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
                druidDataSource.discardConnection(connection);
                druidDataSource.removeAbandoned();
                if (!checkDataSource(dataSource)) {
                    continue;
                }
                dataSourceList.add(dataSource);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!CollectionUtils.isEmpty(dataSourceList)) dataSourceService.insertBatch(dataSourceList);
    }

    private boolean checkDataSource(DataSource dataSource) {
        if (StringUtils.isEmpty(dataSource.getSourceType())) {
            if (dataSourceService.getEntityCount(EntityCriteria.build().eq("parent_id", dataSource.getSourceIp())) > 0) {

            }
        } else {

        }
        DataSource queryDataSource = dataSourceService.getEntity(EntityCriteria.build()
                .eq("source_type", dataSource.getSourceType())
                .eq("source_ip", dataSource.getSourceIp())
                .eq("source_port", dataSource.getSourcePort())
                .eq("data_name", dataSource.getDataName())
                .eq("data_username", dataSource.getDataUsername())
                .eq("data_password", dataSource.getDataPassword()));
        return queryDataSource == null || queryDataSource.getSourceId().equals(dataSource.getSourceId());
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

    @RequestMapping(value = "collection-list", method = RequestMethod.GET)
    public ModelAndView regionList() {
        ModelAndView mv = new ModelAndView("data/data-collection-list");
        return mv;
    }

    @RequestMapping(value = "queryCollection", method = RequestMethod.POST)
    @ResponseBody
    public List<DataCollection> queryCollection(@RequestParam(required = false) Integer id) {
        if (id == null) {
            return dataCollectionService.getEntityList(EntityCriteria.build().isNull("parent_id"));
        } else {
            return dataCollectionService.getEntityList(EntityCriteria.build().eq("parent_id", id));

        }
    }

    @RequestMapping(value = "collection", method = RequestMethod.GET)
    public ModelAndView region(@RequestParam(required = false) Long id, @RequestParam(required = false) Long parentId) {
        ModelAndView mv = new ModelAndView("data/data-collection");
        mv.addObject("collectionTypes", CollectionType.values());
        mv.addObject("dataSources", dataSourceService.getEntityList());
        DataCollection collection = dataCollectionService.getById(id);
        if (collection != null) {
            mv.addObject("collection", collection);
            mv.addObject("parentCollection", dataCollectionService.getById(collection.getParentId()));
        } else {
            mv.addObject("parentCollection", dataCollectionService.getById(parentId));
        }
        return mv;
    }

    @RequestMapping(value = "collection", method = RequestMethod.POST)
    public ModelAndView regionPost(DataCollection collection) {
        ModelAndView mv = new ModelAndView("data/data-collection");
        dataCollectionService.save(collection);
        mv.addObject("success", true);
        mv.addObject("collectionId", collection.getParentId());

        return mv;
    }

    @RequestMapping(value = "collection", method = RequestMethod.DELETE)
    @ResponseBody
    public void regionDelete(@RequestParam Long id) {
        if (dataCollectionService.getEntityCount(EntityCriteria.build().eq("parent_id", id)) > 0) {
            throw new BaseException("020005");
        } else {
            dataCollectionService.deleteById(id);

        }
    }

    @RequestMapping(value = "getCollections", method = RequestMethod.GET)
    @ResponseBody
    public List getCollections(@RequestParam Long id, @RequestParam(required = false, defaultValue = "") String type) {
        CollectionType collectionType = CollectionType.getTypeByCode(type);
        if (CollectionType.TABLE.equals(collectionType) || CollectionType.VIEW.equals(collectionType)) {
            dynamicDataSourceService.putDataSource(id);
            try {
                DruidDataSource dataSource = (DruidDataSource) DBContextHolder.getDataSource();
                Connection connection = dataSource.getConnection();
                List columns = dynamicDataSourceService.loadDBTable(connection, type);
                dataSource.discardConnection(connection);
                dataSource.removeAbandoned();
                return columns;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    @RequestMapping(value = "getCollectionData", method = RequestMethod.GET)
    @ResponseBody
    public PageDto getCollectionData(@RequestParam Integer id, @RequestParam int page,
                                     @RequestParam int limit) {
        DataCollection dataCollection = dataCollectionService.getById(id);
        dynamicDataSourceService.putDataSource(dataCollection.getSourceId());
        Pageable pageable = new PageRequest(page - 1, limit, null);
        Page result = dynamicDataSourceService.getCollectionData(dataCollection, pageable);
        return PageDto.create(result.getTotalElements(), result.getContent());
    }

    @RequestMapping(value = "getCollectionStructure", method = RequestMethod.GET)
    @ResponseBody
    public List getCollectionStructure(@RequestParam Integer id) {
        DataCollection dataCollection = dataCollectionService.getById(id);
        dynamicDataSourceService.putDataSource(dataCollection.getSourceId());
        try {
            DruidDataSource dataSource = (DruidDataSource) DBContextHolder.getDataSource();
            Connection connection = dataSource.getConnection();
            List columns = dynamicDataSourceService.loadDBColumn(connection, dataCollection.getCollectionValue());
            dataSource.discardConnection(connection);
            dataSource.removeAbandoned();
            return columns;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}