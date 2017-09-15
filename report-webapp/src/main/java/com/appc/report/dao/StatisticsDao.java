package com.appc.report.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * UserDao 用户
 *
 * @author : panda
 * @version : Ver 1.0
 * @date : 2017-9-14
 */
@Repository
public interface StatisticsDao {

    List<Map> informationStatistics();
}
