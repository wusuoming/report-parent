        package com.appc.report.dao;
        import com.appc.report.model.PayOrder;
        import com.appc.framework.mybatis.dao.BasicCrudDao;
        import org.springframework.stereotype.Repository;

/**
 *  PayOrderDao 订单
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-9-14
 */
@Repository
public interface PayOrderDao  extends BasicCrudDao<PayOrder>  {

        }
