package com.personal.oyl.event.web.subscribers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.personal.oyl.event.BaseSubscriber;
import com.personal.oyl.event.Event;
import com.personal.oyl.event.order.DailyOrderReport;
import com.personal.oyl.event.order.Order;
import com.personal.oyl.event.order.OrderRepos;

@Component
public class DailyOrderReportSubscriber implements BaseSubscriber {
    
    @Autowired
    private OrderRepos repos;

    @Override
    public void onEvent(Event e) {
        Order order = Order.fromJson(e.getContext());
        DailyOrderReport report = repos.selectDailyOrderReportByKey(new java.sql.Date(order.getOrderTime().getTime()));
        
        if (null == report) {
            report = new DailyOrderReport();
            report.setDay(new java.sql.Date(order.getOrderTime().getTime()));
            report.setOrderNum(1l);
            report.setOrderTotal(new Long(order.getOrderAmount()));
            
            repos.createDailyOrderReport(report);
        } else {
            report.setOrderNum(report.getOrderNum() + 1);
            report.setOrderTotal(report.getOrderTotal() + order.getOrderAmount());
            
            repos.updateDailyOrderReport(report);
        }
    }

}
