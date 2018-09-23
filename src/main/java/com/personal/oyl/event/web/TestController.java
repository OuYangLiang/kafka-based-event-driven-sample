package com.personal.oyl.event.web;

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
    
    @RequestMapping("/newOrder")
    @ResponseBody
    public Object newOrder() {
        Order order = orderFactory.randomOrder();
        
        repos.createOrder(order);
        
        return order;
    }
    
    @RequestMapping("/report")
    @ResponseBody
    public Object report() {
        TestResult result = new TestResult();
        result.setItems(repos.selectAllUserReport());
        result.setDailyItems(repos.selectAllDailyReport());
        
        return result;
    }
}
