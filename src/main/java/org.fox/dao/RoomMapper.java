package org.fox.dao;

import org.fox.entity.Room;

import java.util.List;

public interface RoomMapper {
    /**
     * 根据客房类型返回该类型下所有房间
     * @param roomCategoryId
     * @return Room
     */
    public List<Room> queryRoomList(int roomCategoryId);

    /**
     * 新添房间
     * @param room
     * @return
     */
    public boolean insertRoom(Room room);

    /**
     * 根据roomNo返回具体房间：1.判断是否闲置
     * @param roomNo
     * @return
     */
    public Room queryRoom(String roomNo);

    /**
     * 更新房间状态
     * @param room
     * @return
     */
    public boolean updateRoom(Room room);
}
