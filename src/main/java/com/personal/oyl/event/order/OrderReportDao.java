package com.personal.oyl.event.order;

import java.util.List;

public interface OrderReportDao {
    void insert(OrderReport report);
    
    void update(OrderReport report);
    
    OrderReport selectByKey(Long userId);
    
    List<OrderReport> selectAll();
}
