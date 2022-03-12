package com.lxk.campustourguide.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxk.campustourguide.bean.Edge;
import com.lxk.campustourguide.bean.Graph;
import com.lxk.campustourguide.bean.Point;
import com.lxk.campustourguide.mapper.EdgeMapper;
import com.lxk.campustourguide.mapper.GraphMapper;
import com.lxk.campustourguide.mapper.PointMapper;
import com.lxk.campustourguide.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author lxkstart
 * @date 2022/3/10 - 17:23
 */
@Service
public class GraphServiceImpl extends ServiceImpl<GraphMapper, Graph> implements GraphService {
    @Autowired
    PointMapper pointMapper;
    @Autowired
    EdgeMapper edgeMapper;

    static int N = 10;

    @Override
    public void initGraph(Graph graph) {
        List<Point> pointList = pointMapper.selectList(null);
        List<Edge> edgeList = edgeMapper.selectList(null);
        int[][] matrix = new int[N + 1][N + 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = graph.getINF();
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][i]=0;
        }
        for (Edge edge : edgeList) {
            matrix[edge.getX()][edge.getY()] = edge.getWeight();
            matrix[edge.getY()][edge.getX()] = edge.getWeight();
        }
        Point[] points = new Point[pointList.size()];
        int i = 0;
        for (Point point : pointList) {
            points[i++] = point;
        }
        graph.setPoints(points);
        graph.setEdgeNum(N);
        graph.setmMatrix(matrix);
    }

    int[] prev = new int[N + 1];
    @Override
    public Integer findMinDistance(int vs, int ve, int[][] mMatrix, int len) {

        int[] dist = new int[len + 1];
        Arrays.fill(dist,0x3f3f3f3f);
        boolean[] flag = new boolean[len + 1];
        for (int i = 1; i <= len; i++) {
            flag[i] = false;
            prev[i] = 0;
            dist[i] = mMatrix[vs][i];
            if(dist[i]!=0x3f3f3f3f){
                prev[i]=vs;
            }
        }
        flag[vs] = true;
        dist[vs] = 0;

        int k = 0;
        for (int i = 1; i < len; i++) {
            int min = 0x3f3f3f3f;
            for (int j = 1; j <= len; j++) {
                if (!flag[j] && dist[j] < min) {
                    min = dist[j];
                    k = j;
                }
            }
            flag[k] = true;
            for (int j = 1; j <= len; j++) {
                int tmp = mMatrix[k][j] == 0x3f3f3f3f ? 0x3f3f3f3f : (min + mMatrix[k][j]);
                if (!flag[j] && dist[j] > tmp) {
                    dist[j] = tmp;
                    prev[j] = k;
                }
            }
        }
        return dist[ve];
    }

    @Override
    public String printPath(int k, int x) {
        int[] arr=new int[N+100];
        String path="";
        int cnt=1,ex=x;
        if(k==x){
            path+=findNameById(k);
        }
        else if(prev[x]==0){
            path+=findNameById(k);
        }
        else{
            while (x!=k){
                arr[cnt++]=x;
                x=prev[x];
            }
            arr[cnt]=x;
            for (int i = cnt; i > 1 ; i--) {
                path+=findNameById(arr[i]);
                path+="-->";
            }
            path+=findNameById(arr[1]);
        }
        return path;
    }

    @Override
    public int findIdByName(String name) {
        List<Point> points = pointMapper.selectList(null);
        for (Point point : points) {
            if (point.getName().equals(name)) {
                return point.getId();
            }
        }
        return 0;
    }

    @Override
    public String findNameById(int id) {
        List<Point> points = pointMapper.selectList(null);
        for (Point point : points) {
            if (point.getId() == id) {
                return point.getName();
            }
        }
        return null;
    }

    @Override
    public Point findPointByName(String name) {
        QueryWrapper<Point> wrapper = new QueryWrapper<>();
        wrapper.eq("name",name);
        Point point = pointMapper.selectOne(wrapper);
        return point;
    }

}
