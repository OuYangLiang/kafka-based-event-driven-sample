package com.personal.oyl.event.web;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.personal.oyl.event.order.Order;
import com.personal.oyl.event.order.OrderFactory;
import com.personal.oyl.event.order.OrderRepos;

@Controller
@RequestMapping("/test")
public class TestController {
    
    @Autowired
    private OrderRepos repos;
    
    @Autowired
    private OrderFactory orderFactory;
    
    @RequestMapping("/createOrder")
    @ResponseBody
    public Object hello() {
        for (int i = 1; i <= 1000; i++) {
            Order order = orderFactory.randomOrder();
            
            repos.createOrder(order);
        }
        
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        TestResult result = new TestResult();
        result.setItems(repos.selectAllReport());
        
        return result;
    }
}
