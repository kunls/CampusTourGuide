package com.lxk.campustourguide.bean;

/**
 * @author lxkstart
 * @date 2022/3/10 - 16:42
 */
public class Graph {
    private int edgeNum;
    private Point[] points;
    private int[][] mMatrix;
    private int INF=0x3f3f3f3f;

    public int getEdgeNum() {
        return edgeNum;
    }

    public void setEdgeNum(int edgeNum) {
        this.edgeNum = edgeNum;
    }

    public Point[] getPoints() {
        return points;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }

    public int[][] getmMatrix() {
        return mMatrix;
    }

    public void setmMatrix(int[][] mMatrix) {
        this.mMatrix = mMatrix;
    }

    public int getINF() {
        return INF;
    }

    public void setINF(int INF) {
        this.INF=INF;
    }

    public Graph() {
    }

    public Graph(int edgeNum, Point[] points, int[][] mMatrix) {
        this.edgeNum = edgeNum;
        this.points = points;
        this.mMatrix = mMatrix;
    }
}
