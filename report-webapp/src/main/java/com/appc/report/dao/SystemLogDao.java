        package com.appc.report.dao;
        import com.appc.report.model.SystemLog;
        import com.appc.framework.mybatis.dao.BasicCrudDao;
        import org.springframework.stereotype.Repository;

/**
 *  SystemLogDao 
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-9-14
 */
@Repository
public interface SystemLogDao  extends BasicCrudDao<SystemLog>  {

        }
