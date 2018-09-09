package com.personal.oyl.event.web;

import java.util.List;

import com.personal.oyl.event.order.OrderReport;

public class TestResult {
    private List<OrderReport> items;
    private long totalOrders;
    private long totalAmount;

    public List<OrderReport> getItems() {
        return items;
    }

    public void setItems(List<OrderReport> items) {
        this.items = items;

        if (null != items) {
            for (OrderReport report : items) {
                totalOrders += report.getOrderNum();
                totalAmount += report.getOrderTotal();
            }
        }
    }

    public long getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(long totalOrders) {
        this.totalOrders = totalOrders;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

}
