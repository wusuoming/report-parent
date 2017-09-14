        package com.appc.report.dao;
        import com.appc.report.model.Contacts;
        import com.appc.framework.mybatis.dao.BasicCrudDao;
        import org.springframework.stereotype.Repository;

/**
 *  ContactsDao 联系人
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-9-14
 */
@Repository
public interface ContactsDao  extends BasicCrudDao<Contacts>  {

        }
