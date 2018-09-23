package com.personal.oyl.event.web;

import java.util.List;

import com.personal.oyl.event.order.DailyOrderReport;
import com.personal.oyl.event.order.UserOrderReport;

public class TestResult {
    private List<UserOrderReport> items;
    private List<DailyOrderReport> dailyItems;
    private long totalOrders;
    private long totalAmount;

    public List<UserOrderReport> getItems() {
        return items;
    }

    public void setItems(List<UserOrderReport> items) {
        this.items = items;

        if (null != items) {
            for (UserOrderReport report : items) {
                totalOrders += report.getOrderNum();
                totalAmount += report.getOrderTotal();
            }
        }
    }

    public List<DailyOrderReport> getDailyItems() {
        return dailyItems;
    }

    public void setDailyItems(List<DailyOrderReport> dailyItems) {
        this.dailyItems = dailyItems;
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
