package com.personal.oyl.event.web.subscribers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.personal.oyl.event.BaseSubscriber;
import com.personal.oyl.event.Event;
import com.personal.oyl.event.EventMapper;
import com.personal.oyl.event.order.DailyOrderReport;
import com.personal.oyl.event.order.Order;
import com.personal.oyl.event.order.OrderRepos;

@Component("dailyOrderReportSubscriber")
public class DailyOrderReportSubscriber implements BaseSubscriber {
    
    private static final Logger log = LoggerFactory.getLogger(DailyOrderReportSubscriber.class);
    
    @Autowired
    private OrderRepos repos;
    
    @Autowired
    private EventMapper eventMapper;
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    @Override
    public void onEvent(Event e) {
        
        try {
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
            
            eventMapper.archive("000000000002", e);
        } catch (DuplicateKeyException ex) {
            log.warn("Duplicated message " + e.json());
        }
        
    }

}
