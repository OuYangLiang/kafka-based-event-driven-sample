package com.personal.oyl.event.order;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Order {
    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    
    private Long id;
    private Long userId;
    private Date orderTime;
    private Integer orderAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }
    
    public String json() {
        String rlt = gson.toJson(this);
        return rlt;
    }
    
    public static Order fromJson(String json) {
        return gson.fromJson(json, Order.class);
    }
    
    public static void main(String[] args) {
        Order order = new Order();
        order.setId(1l);
        order.setUserId(21l);
        order.setOrderTime(new Date());
        order.setOrderAmount(100);
        
        String json = order.json();
        
        Order order2 = Order.fromJson(json);
        String json2 = order2.json();
        
        System.out.println(json);
        System.out.println(json2);
    }

}
