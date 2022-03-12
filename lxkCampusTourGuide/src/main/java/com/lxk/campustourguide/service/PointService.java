package com.lxk.campustourguide.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lxk.campustourguide.bean.Point;

import java.util.List;

/**
 * @author lxkstart
 * @date 2022/3/11 - 18:17
 */
public interface PointService extends IService<Point> {
    void addVisitor(int id);
    List<Point> sortByVisitors();
}
