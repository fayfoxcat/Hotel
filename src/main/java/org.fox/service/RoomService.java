package org.fox.service;

import org.fox.entity.Room;

public interface RoomService {
    /**
     * 根据客房类型返回具体客房信息
     * @param roomCategoryId
     * @return Room
     */
    public Room queryRoom(int roomCategoryId);

    /**
     * 新添房间
     * @param room
     * @return
     */
    public boolean addRoom(Room room);
}
