package org.fox.dao;

import org.fox.entity.Order;

import java.util.List;

public interface OrderMapper {
    /**
     * 根据OrderId获取单个订单
     * @param orderNo
     * @return Order
     */
    public Order queryOrder(String orderNo);

    /**
     * 根据orderUser（用户id）获取所有订单
     * @param orderUser
     * @return Order
     */
    public List<Order> queryUserOrder(int orderUser);

    /**
     * 用户提交订单入库
     * @param order
     * @return int
     */
    public boolean insertUserOrder(Order order);

    /**
     * 根据订单号orderNo删除订单
     * @param orderNo
     * @return
     */
    public boolean deleteOrder(int orderNo);
}
