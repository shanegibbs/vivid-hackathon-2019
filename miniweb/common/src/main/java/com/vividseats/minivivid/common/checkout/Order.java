package com.vividseats.minivivid.common.checkout;

public class Order {

    private int OrderId;
    private int UserId;
    private String Status;
    private int Price;
    private String Item;

    public Order(int orderId, int userId, String status, int price, String item) {
        OrderId = orderId;
        UserId = userId;
        Status = status;
        Price = price;
        Item = item;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
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
