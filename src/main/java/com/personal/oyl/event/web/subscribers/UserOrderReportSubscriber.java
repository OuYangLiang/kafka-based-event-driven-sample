package com.personal.oyl.event.web.subscribers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.personal.oyl.event.EventSubscriber;
import com.personal.oyl.event.Event;
import com.personal.oyl.event.EventMapper;
import com.personal.oyl.event.order.Order;
import com.personal.oyl.event.order.UserOrderReport;
import com.personal.oyl.event.order.OrderRepos;

@Component("userOrderReportSubscriber")
public class UserOrderReportSubscriber implements EventSubscriber {

    private static final Logger log = LoggerFactory.getLogger(UserOrderReportSubscriber.class);
    
    @Autowired
    private OrderRepos repos;
    
    @Autowired
    private EventMapper eventMapper;
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    @Override
    public void onEvent(Event e) {
        
        try {
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
            
            eventMapper.archive(this.id(), e);
        } catch (DuplicateKeyException ex) {
            log.warn("Duplicated message " + e.json());
        }
        
    }

    @Override
    public String id() {
        return "000000000001";
    }

}
