package com.vividseats.minivivid.common.checkout;

import javax.persistence.Entity;

@Entity
@SuppressWarnings("unused")
public class Order {

    private String OrderId;
    private String UserId;
    private String Status;
    private int Price;
    private String Item;

    public Order() {
    }

    public Order(String orderId, String userId, String status, int price, String item) {
        OrderId = orderId;
        UserId = userId;
        Status = status;
        Price = price;
        Item = item;
    }

    @Override
    public String toString() {
        return "Order{" +
                "OrderId=" + OrderId +
                ", UserId=" + UserId +
                ", Status='" + Status + '\'' +
                ", Price=" + Price +
                ", Item='" + Item + '\'' +
                '}';
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getItem() {
        return Item;
    }

    public void setItem(String item) {
        Item = item;
    }

}
