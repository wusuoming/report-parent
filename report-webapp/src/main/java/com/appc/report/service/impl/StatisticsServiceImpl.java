package com.appc.report.service.impl;

import com.appc.report.dao.StatisticsDao;
import com.appc.report.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * UserDao 用户
 *
 * @author : panda
 * @version : Ver 1.0
 * @date : 2017-9-14
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private StatisticsDao statisticsDao;

    public List<Map> informationStatistics() {
        return statisticsDao.informationStatistics();
    }
}
