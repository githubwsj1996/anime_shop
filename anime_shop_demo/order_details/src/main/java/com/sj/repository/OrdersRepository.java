package com.sj.repository;

import com.sj.entity.Orders;
import com.sj.entity.OrdersLook;
import org.springframework.stereotype.Repository;


@Repository
public interface OrdersRepository {
    /*查询订单*/
    public OrdersLook findById(String orderId);
    /*添加订单*/
    public int saveOrders(Orders orders);
    /*修改订单的支付状态*/
    public int updateStateSaveCause(String orderId,Integer state,Integer causeId);
    /*删除订单*/
    public int deleteOrder(String orderId);


}
