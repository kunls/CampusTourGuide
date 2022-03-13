package com.lxk.campustourguide.controller;

import com.lxk.campustourguide.bean.Graph;
import com.lxk.campustourguide.bean.Point;
import com.lxk.campustourguide.bean.Tourist;
import com.lxk.campustourguide.mapper.TouristMapper;
import com.lxk.campustourguide.service.GraphService;
import com.lxk.campustourguide.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author lxkstart
 * @date 2022/3/9 - 22:49
 */
@Controller
public class MyController {
    @Autowired
    TouristMapper touristMapper;
    @Autowired
    GraphService graphService;
    @Autowired
    PointService pointService;

    @RequestMapping("/")
    public String index(HttpSession session){
        List<Point> pointList = pointService.sortByVisitors();
        session.setAttribute("pointList",pointList);
        return "index";
    }
    @PostMapping("/index")
    public String getMessage(String touristName, String currentLocation, String destination,HttpSession session){
        Tourist tourist = new Tourist(touristName);

        pointService.addVisitor(graphService.findIdByName(destination));
        touristMapper.insert(tourist);

        String image="images/"+destination+".webp";
        session.setAttribute("image",image);
        session.setAttribute("currentLocation",currentLocation);
        session.setAttribute("destination",destination);
        session.setAttribute("numVisitors",graphService.findPointByName(destination).getNumVisitors());
        session.setAttribute("introduction",graphService.findPointByName(destination).getIntroduction());

        return "redirect:show.html";
    }
    //防止表单重复提交
    @RequestMapping("/show.html")
    public String showMessage(Model model, HttpSession session){
        String currentLocation = (String) session.getAttribute("currentLocation");
        String destination = (String) session.getAttribute("destination");

        Graph graph = new Graph();

        graphService.initGraph(graph);

        int vs = graphService.findIdByName(currentLocation);
        int ve = graphService.findIdByName(destination);
        Integer minDistance = graphService.findMinDistance(vs, ve, graph.getmMatrix(), graph.getEdgeNum());

        model.addAttribute("minDistance",minDistance);
        String wholePath = graphService.printPath(vs, ve);
        model.addAttribute("wholePath",wholePath);
        return "show";
    }
}
