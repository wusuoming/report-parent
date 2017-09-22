package com.appc.report.service.impl;

import basic.common.core.exception.BaseException;
import basic.common.core.id.IdUtil;
import com.appc.framework.mybatis.executor.criteria.EntityCriteria;
import com.appc.report.dao.CommonRegionDao;
import com.appc.report.model.CommonRegion;
import com.appc.report.service.CommonRegionService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * CommonRegionServiceImpl
 *
 * @author : panda
 * @version : Ver 1.0
 * @date : 2017-9-14
 */
@Service
public class CommonRegionServiceImpl extends CommonServiceImpl<CommonRegion, CommonRegionDao> implements CommonRegionService {


    @Override
    public void save(CommonRegion commonRegion) {

        if (!StringUtils.isEmpty(commonRegion.getCommonRegionId())) {
            checkCommonRegion(commonRegion);
            buildParent(commonRegion);
            updateById(commonRegion);
        } else {
            commonRegion.setCreateTime(new Date());
            commonRegion.setCommonRegionId(IdUtil.getId() + "");
            buildParent(commonRegion);
            insert(commonRegion);
        }
    }

    private void checkCommonRegion(CommonRegion commonRegion) {
        CommonRegion dbCommonRegion = getById(commonRegion.getCommonRegionId());
        if (!((StringUtils.isEmpty(commonRegion.getParentRegionId()) && StringUtils.isEmpty(dbCommonRegion.getParentRegionId()))
                || (!StringUtils.isEmpty(commonRegion.getParentRegionId()) && commonRegion.getParentRegionId().equals(dbCommonRegion.getParentRegionId()))
                || (!StringUtils.isEmpty(dbCommonRegion.getParentRegionId()) && dbCommonRegion.getParentRegionId().equals(commonRegion.getParentRegionId())))) {
            if (getEntityCount(EntityCriteria.build().eq("parent_region_id", commonRegion.getCommonRegionId())) > 0) {
                throw new BaseException("020004");
            }
        }
    }

    void buildParent(CommonRegion commonRegion) {
        CommonRegion parent = getById(commonRegion.getParentRegionId());
        if (parent != null) {
            commonRegion.setRegionPath(parent.getRegionPath() + "," + commonRegion.getCommonRegionId());
        } else {
            commonRegion.setRegionPath(commonRegion.getCommonRegionId());
        }
    }
}
