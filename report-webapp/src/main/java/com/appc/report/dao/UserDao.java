        package com.appc.report.dao;
        import com.appc.report.model.User;
        import com.appc.framework.mybatis.dao.BasicCrudDao;
        import org.springframework.stereotype.Repository;

/**
 *  UserDao 用户
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-9-14
 */
@Repository
public interface UserDao  extends BasicCrudDao<User>  {

        }
