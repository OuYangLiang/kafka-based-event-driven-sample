package com.personal.oyl.event.order;

import java.util.List;

public interface UserOrderReportDao {
    void insert(UserOrderReport report);
    
    void update(UserOrderReport report);
    
    UserOrderReport selectByKey(Long userId);
    
    List<UserOrderReport> selectAll();
}
