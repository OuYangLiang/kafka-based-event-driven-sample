package com.personal.oyl.event.web.subscribers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.personal.oyl.event.BaseSubscriber;
import com.personal.oyl.event.Event;
import com.personal.oyl.event.order.Order;
import com.personal.oyl.event.order.OrderReport;
import com.personal.oyl.event.order.OrderRepos;

@Component
public class Sub1 implements BaseSubscriber {
    
    @Autowired
    private OrderRepos repos;

    @Override
    public void onEvent(Event e) {
        Order order = Order.fromJson(e.getContext());
        OrderReport report = repos.selectOrderReportByKey(order.getUserId());
        
        if (null == report) {
            report = new OrderReport();
            report.setUserId(order.getUserId());
            report.setOrderNum(1l);
            report.setOrderTotal(new Long(order.getOrderAmount()));
            
            repos.createOrderReport(report);
        } else {
            report.setOrderNum(report.getOrderNum() + 1);
            report.setOrderTotal(report.getOrderTotal() + order.getOrderAmount());
            
            repos.updateOrderReport(report);
        }
    }

}
