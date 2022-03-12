package com.lxk.campustourguide.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lxk.campustourguide.bean.Graph;
import com.lxk.campustourguide.bean.Point;

/**
 * @author lxkstart
 * @date 2022/3/10 - 17:18
 */
public interface GraphService extends IService<Graph> {
    void initGraph(Graph graph);
    Integer findMinDistance(int vs, int ve, int[][] mMatrix, int len);
    String printPath(int k,int x);
    int findIdByName(String name);
    String findNameById(int id);
    Point findPointByName(String name);
}
