package com.lxk.campustourguide.bean;

/**
 * @author lxkstart
 * @date 2022/3/8 - 17:20
 */
public class Point {
    private int id;
    private String name;
    private int numVisitors;

    public int getNumVisitors() {
        return numVisitors;
    }

    public void setNumVisitors(int numVisitors) {
        this.numVisitors = numVisitors;
    }

    public Point(int id, String name, int numVisitors, String introduction) {
        this.id = id;
        this.name = name;
        this.numVisitors = numVisitors;
        this.introduction = introduction;
    }

    private String introduction;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Point() {
    }

    public Point(int id, String name, String introduction) {
        this.id = id;
        this.name = name;
        this.introduction = introduction;
    }
}
