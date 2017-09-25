package com.appc.report.service.impl;

import basic.common.core.exception.BaseException;
import com.appc.framework.mybatis.executor.criteria.EntityCriteria;
import com.appc.report.dao.DataCollectionDao;
import com.appc.report.model.DataCollection;
import com.appc.report.service.DataCollectionService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * DataCollectionServiceImpl
 *
 * @author : panda
 * @version : Ver 1.0
 * @date : 2017-9-25
 */
@Service
public class DataCollectionServiceImpl extends CommonServiceImpl<DataCollection, DataCollectionDao> implements DataCollectionService {


    @Override
    public void save(DataCollection collection) {
        if (!StringUtils.isEmpty(collection.getCollectionId())) {
            checkDataCollection(collection);
            buildParent(collection);
            updateById(collection);
        } else {
            collection.setCreateTime(new Date());
            insert(collection);
            buildParent(collection);
            DataCollection updateCollection = new DataCollection();
            updateCollection.setCollectionId(collection.getCollectionId());
            updateCollection.setPath(collection.getPath());
            updateById(updateCollection);
        }
    }

    private void buildParent(DataCollection collection) {
        DataCollection parent = getById(collection.getParentId());
        if (parent != null) {
            collection.setPath(parent.getPath() + "," + collection.getCollectionId());
        } else {
            collection.setPath(collection.getCollectionId().toString());
        }
    }

    private void checkDataCollection(DataCollection collection) {
        DataCollection dbCommonRegion = getById(collection.getCollectionId());
        if (!((StringUtils.isEmpty(collection.getParentId()) && StringUtils.isEmpty(dbCommonRegion.getParentId()))
                || (!StringUtils.isEmpty(collection.getParentId()) && collection.getParentId().equals(dbCommonRegion.getParentId()))
                || (!StringUtils.isEmpty(dbCommonRegion.getParentId()) && dbCommonRegion.getParentId().equals(collection.getParentId())))) {
            if (getEntityCount(EntityCriteria.build().eq("parent_id", collection.getCollectionId())) > 0) {
                throw new BaseException("020004");
            }
        }
    }
}
