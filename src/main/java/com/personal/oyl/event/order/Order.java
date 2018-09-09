package com.personal.oyl.event.order;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Order {
    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    
    private Long id;
    private Long userId;
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

}
