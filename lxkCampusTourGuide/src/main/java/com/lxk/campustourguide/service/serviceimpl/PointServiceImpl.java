package com.lxk.campustourguide.service.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxk.campustourguide.bean.Point;
import com.lxk.campustourguide.mapper.PointMapper;
import com.lxk.campustourguide.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author lxkstart
 * @date 2022/3/11 - 18:18
 */
@Service
public class PointServiceImpl extends ServiceImpl<PointMapper, Point> implements PointService{
    @Autowired
    PointMapper pointMapper;
    //二分查找
    @Override
    public void addVisitor(int id) {
        List<Point> pointList = pointMapper.selectList(null);
        int left=0,right=pointList.size()-1,mid;
        while (left<=right){
            mid=(left+right)/2;
            if(pointList.get(mid).getId()==id){
                pointList.get(mid).setNumVisitors(pointList.get(mid).getNumVisitors()+1);
                Point point = pointList.get(mid);
                pointMapper.updateById(point);
                return;
            }
            else if(pointList.get(mid).getId()>id){
                right=mid-1;
            }
            else{
                left=mid+1;
            }
        }
    }

    @Override
    public List<Point> sortByVisitors() {
        List<Point> pointList = pointMapper.selectList(null);
        Point[] points=new Point[pointList.size()];
        for (int i = 0; i < pointList.size(); i++) {
            points[i]=pointList.get(i);
        }
        quickSort(points,0,points.length-1);
        return Arrays.asList(points);
    }
    //快速排序
    public static void quickSort(Point[] points,int low,int high){
        int i=low,j=high;
        if(i>j){
            return;
        }
        Point temp=points[low];
        while (i<j){
            while (i<j&&points[j].getNumVisitors()<=temp.getNumVisitors()){
                j--;
            }
            while (i<j&&points[i].getNumVisitors()>=temp.getNumVisitors()){
                i++;
            }
            if(i<j){
                Point t=points[i];
                points[i]=points[j];
                points[j]=t;
            }
        }
        points[low]=points[i];
        points[i]=temp;
        quickSort(points,low,j-1);
        quickSort(points,i+1,high);
    }
}
