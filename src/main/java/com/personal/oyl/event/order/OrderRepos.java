package com.personal.oyl.event.order;

import java.util.List;

public interface OrderRepos {
    void createOrder(Order order);
    
    OrderReport selectOrderReportByKey(Long userId);
    
    List<OrderReport> selectAllReport();
    
    void createOrderReport(OrderReport report);
    
    void updateOrderReport(OrderReport report);
}
