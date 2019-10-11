package org.fox.service.impl;

import org.fox.dao.RoomMapper;
import org.fox.entity.Room;
import org.fox.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService{

    @Autowired
    private RoomMapper roomMapper;

    /**
     *
     * @param roomCategoryId
     * @return
     */
    @Override
    public Room queryRoom(int roomCategoryId) {
        return null;
    }

    /**
     *
     * @param room
     * @return
     */
    @Override
    public boolean addRoom(Room room) {
        return roomMapper.insertRoom(room);
    }
}
