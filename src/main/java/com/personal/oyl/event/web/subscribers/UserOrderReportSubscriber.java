package com.personal.oyl.event.web.subscribers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.personal.oyl.event.BaseSubscriber;
import com.personal.oyl.event.Event;
import com.personal.oyl.event.order.Order;
import com.personal.oyl.event.order.UserOrderReport;
import com.personal.oyl.event.order.OrderRepos;

@Component
public class UserOrderReportSubscriber implements BaseSubscriber {
    
    @Autowired
    private OrderRepos repos;

    @Override
    public void onEvent(Event e) {
        Order order = Order.fromJson(e.getContext());
        UserOrderReport report = repos.selectUserOrderReportByKey(order.getUserId());
        
        if (null == report) {
            report = new UserOrderReport();
            report.setUserId(order.getUserId());
            report.setOrderNum(1l);
            report.setOrderTotal(new Long(order.getOrderAmount()));
            
            repos.createUserOrderReport(report);
        } else {
            report.setOrderNum(report.getOrderNum() + 1);
            report.setOrderTotal(report.getOrderTotal() + order.getOrderAmount());
            
            repos.updateUserOrderReport(report);
        }
    }

}
