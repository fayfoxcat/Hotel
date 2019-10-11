package org.fox.service.impl;

import org.fox.dao.OrderMapper;
import org.fox.dao.RoomMapper;
import org.fox.entity.Order;
import org.fox.entity.Room;
import org.fox.service.OrderService;
import org.fox.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RoomMapper roomMapper;
    /**
     * 根据orderUser获取当前用户所有订单
     * @param orderUser
     * @return
     */
    @Override
    public List<Order> queryUserOrder(int orderUser) {
        return orderMapper.queryUserOrder(orderUser);
    }

    /**
     *根据OrderId获取单个订单详情
     * @param orderNo
     * @return
     */
    @Override
    public Order queryOrder(String orderNo) {
        return orderMapper.queryOrder(orderNo);
    }

    /**
     * 用户提交订单
     * @param order
     * @return
     */
    @Override
    public boolean addOrder(Order order,int roomCategoryId) {
        order.setOrderCreatetime(new Date());
        //调用方法根据时间+随机数生成订单号
        String orderNo = ImageUtil.getRandomFileName() ;
        order.setOrderNo(orderNo);
        //订单默认可用
        order.setOrderStatus(1);
        //随机分配房间
        List<Room> rooms = roomMapper.queryRoomList(roomCategoryId);
        //随机获取roomStatus(空闲的房间)
        for (Room room : rooms) {
            if(roomMapper.queryRoom(room.getRoomNo()).getRoomStatus() == 1){
                order.setRoomNo(room.getRoomNo());
                //把该房间状态改为 0 (不可用)
                room.setRoomStatus(0);
                //把userid修改为当前用户
                room.setUserId(order.getOrderUser());
                roomMapper.updateRoom(room);
                break;
            }
        }
        return orderMapper.insertUserOrder(order);
    }

    @Override
    public boolean deleteOrder(int orderNo) {
        return orderMapper.deleteOrder(orderNo);
    }
}
