package com.lxk.campustourguide.bean;

/**
 * @author lxkstart
 * @date 2022/3/11 - 23:35
 */
public class Tourist {
    String touristName;

    public String getTouristName() {
        return touristName;
    }

    public void setTouristName(String touristName) {
        this.touristName = touristName;
    }

    public Tourist() {
    }

    public Tourist(String touristName) {
        this.touristName = touristName;
    }
}
