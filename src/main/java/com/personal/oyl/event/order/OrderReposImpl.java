package com.personal.oyl.event.order;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.personal.oyl.event.EventPublisher;

@Component
public class OrderReposImpl implements OrderRepos {
    
    @Autowired
    private OrderDao dao;
    
    @Autowired
    private EventPublisher publisher;
    
    @Autowired
    private OrderReportDao reportDao;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    @Override
    public void createOrder(Order order) {
        dao.insert(order);
        publisher.publish("o_c", new Date(), order.json(), order.getUserId().intValue());
    }

    @Override
    public OrderReport selectOrderReportByKey(Long userId) {
        return reportDao.selectByKey(userId);
    }

    @Override
    public List<OrderReport> selectAllReport() {
        return reportDao.selectAll();
    }

    @Override
    public void createOrderReport(OrderReport report) {
        reportDao.insert(report);
    }

}
