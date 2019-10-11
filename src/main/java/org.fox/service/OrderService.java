package org.fox.service;

import org.fox.entity.Order;

import java.util.List;

public interface OrderService {
    /**
     * 根据orderUser返回当前登录用户的订单
     * @param orderUser
     * @return
     */
    public List<Order> queryUserOrder(int orderUser);

    /**
     * 根据orderNo返回订单详细信息
     * @param orderNo
     * @return
     */
    public Order queryOrder(String orderNo);

    /**
     * 用户提交订单
     * @param order
     * @param roomCategoryId
     * @return
     */
    public boolean addOrder(Order order,int roomCategoryId);

    /**
     * 根据订单号orderNo删除订单
     * @param orderNo
     * @return
     */
    public boolean deleteOrder(int orderNo);
}