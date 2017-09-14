        package com.appc.report.dao;
        import com.appc.report.model.Menu;
        import com.appc.framework.mybatis.dao.BasicCrudDao;
        import org.springframework.stereotype.Repository;

/**
 *  MenuDao 菜单
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-9-14
 */
@Repository
public interface MenuDao  extends BasicCrudDao<Menu>  {

        }
