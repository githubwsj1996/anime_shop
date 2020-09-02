package com.sj.repository;

import com.sj.entity.OrderDetails;
import com.sj.entity.OrderDetailsSave;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface OrderDetailsRepository {
    /*立即购买关键订单*/
    public int promptlySaveDetails(OrderDetailsSave orderDetailsSave);
    /*查询订单*/
    public List<OrderDetails> findPromptlyOrder (String orderId);

    /*购物车结算,批量创建订单*/
    public void cartSaveDetails(List OrderDetailsList);
    /*查询订单总价*/
    public BigDecimal sumTotalPrice(String orderId);

    /*用户所有订单*/
    public List<OrderDetails> findNoByState(Integer userId,Integer state);

    /*查询订单信息根据订单状态*/
    public List<OrderDetails> findDetailsByState(String orderId,Integer state);

    /*删除订单*/
    public int deleteDetails(String orderId);

    /*统计成交最多的商品*/
    public List<OrderDetails> countDetailsByAnimeId();
}
