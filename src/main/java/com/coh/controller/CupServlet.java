package com.coh.controller;

import com.alibaba.fastjson.JSON;
import com.coh.pojo.Cup;
import com.coh.pojo.PageInfo;
import com.coh.service.CupService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/cup")
public class CupServlet extends HttpServlet {
    CupService cupService;
    @Override
    public void init() throws ServletException {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("app.xml");
        cupService=(CupService)applicationContext.getBean("cupServiceImpl");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req,resp);
    }

    public void  list(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Cup> cupList=cupService.findAll();
        System.out.println(cupList);
        String string =JSON.toJSONString(cupList);
        PrintWriter printWriter=resp.getWriter();
        printWriter.println(string);
        printWriter.flush();
        printWriter.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method=req.getParameter("method");

        switch (method){
            case "list":
                list(req,resp);
                break;
            case "findByPage":
                findByPage(req,resp);
            case "queryByPage":
                queryByPage(req,resp);
        }
    }
    private void findByPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pageNumber=req.getParameter("pageNumber");
        int pageNum=1;
        if(pageNumber!=null && !"".equals(pageNumber)){
            pageNum=Integer.parseInt(pageNumber);
        }
        PageInfo pageInfo=new PageInfo();
        pageInfo.setPageNumber(pageNum);
        List<Cup> cups=cupService.findByPage(pageInfo);
        Map<String ,Object> map=new HashMap<>();
        map.put("pageInfo",pageInfo);
        map.put("cups",cups);

        String string =JSON.toJSONString(map);
        PrintWriter printWriter=resp.getWriter();
        printWriter.println(string);
        printWriter.flush();
        printWriter.close();
    }

    private void queryByPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pageNumber=req.getParameter("PageNum");
        String CupName=req.getParameter("CupName");
        String CupBrand=req.getParameter("CupBrand");
        System.out.println("获取参数pageNum:"+pageNumber+" cupName: "+CupName+" cupBrand:"+CupBrand);
        int pageNum = 1;
        if(pageNumber!=null && !"".equals(pageNumber)){
            pageNum=Integer.parseInt(pageNumber);
        }
        PageInfo pageInfo=new PageInfo();
        pageInfo.setPageNumber(pageNum);
        List<Cup> cups=cupService.queryByPage(pageInfo,CupBrand,CupName);
        System.out.println("查询结果："+cups);
        Map<String ,Object> map=new HashMap<>();
        map.put("pageInfo",pageInfo);
        map.put("cups",cups);

        String string =JSON.toJSONString(map);
        PrintWriter printWriter=resp.getWriter();
        printWriter.println(string);
        printWriter.flush();
        printWriter.close();
    }
}
