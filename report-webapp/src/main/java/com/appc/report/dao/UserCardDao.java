        package com.appc.report.dao;
        import com.appc.report.model.UserCard;
        import com.appc.framework.mybatis.dao.BasicCrudDao;
        import org.springframework.stereotype.Repository;

/**
 *  UserCardDao 用户卡片
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-9-14
 */
@Repository
public interface UserCardDao  extends BasicCrudDao<UserCard>  {

        }
