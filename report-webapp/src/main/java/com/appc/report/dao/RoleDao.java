        package com.appc.report.dao;

        import com.appc.framework.mybatis.dao.BasicCrudDao;
        import com.appc.report.model.Role;
        import org.springframework.stereotype.Repository;

        /**
         *  RoleDao
         *
         * @version : Ver 1.0
         * @author	: panda
         * @date	: 2017-9-19
         */
        @Repository
        public interface RoleDao  extends BasicCrudDao<Role>  {

                }
