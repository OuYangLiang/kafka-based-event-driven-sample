package com.personal.oyl.event.order;

import com.personal.oyl.event.web.AppContext;

public class OrderReport {
    private Long userId;
    private Long orderNum;
    private Long orderTotal;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    public Long getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Long orderTotal) {
        this.orderTotal = orderTotal;
    }
    
    public void update() {
        OrderReportDao dao = AppContext.getContext().getBean(OrderReportDao.class);
        dao.update(this);
    }
}
