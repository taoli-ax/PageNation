package com.coh.test;


import com.coh.pojo.Cup;
import com.coh.service.CupService;
import com.coh.service.impl.CupServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class AppTest {
    @Test
    public void TestCase1(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("app.xml");
        CupService cupService =(CupService) applicationContext.getBean("cupServiceImpl");
        List<Cup> cupList=cupService.findAll();
        System.out.println(cupList);
        System.out.println("hello world");
    }
}
