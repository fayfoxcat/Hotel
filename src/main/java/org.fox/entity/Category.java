package org.fox.entity;

public class Category {
    private int roomCategoryid;
    private String roomCategory;
    private String roomImg;
    private String roomDesc;
    private int roomCount;
    private int roomPrice;
    public int getRoomCategoryid() {
        return roomCategoryid;
    }

    public void setRoomCategoryid(int roomCategoryid) {
        this.roomCategoryid = roomCategoryid;
    }

    public String getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(String roomCategory) {
        this.roomCategory = roomCategory;
    }

    public String getRoomImg() {
        return roomImg;
    }

    public void setRoomImg(String roomImg) {
        this.roomImg = roomImg;
    }

    public String getRoomDesc() {
        return roomDesc;
    }

    public void setRoomDesc(String roomDesc) {
        this.roomDesc = roomDesc;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    public int getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(int roomPrice) {
        this.roomPrice = roomPrice;
    }
}
