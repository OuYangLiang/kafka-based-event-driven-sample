package com.personal.oyl.event.order;

import java.sql.Date;
import java.util.List;

public interface DailyOrderReportDao {
    void insert(DailyOrderReport report);
    
    void update(DailyOrderReport report);
    
    DailyOrderReport selectByKey(Date day);
    
    List<DailyOrderReport> selectAll();
}
