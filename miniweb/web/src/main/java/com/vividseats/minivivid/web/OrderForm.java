package com.vividseats.minivivid.web;

import com.vividseats.minivivid.common.checkout.Order;

import java.util.UUID;

public class OrderForm {

    private String userId;
    private String price;
    private String item;

    public OrderForm(String orderId, String userId, String status, String price, String item) {
        this.userId = userId;
        this.price = price;
        this.item = item;
    }

    public OrderForm() {
    }

    public Order toOrder() {
        int parsedPrice = (int)(Float.parseFloat(price) * 100);
        UUID uuid = UUID.randomUUID();
        String orderId = uuid.toString();

        return new Order(orderId, userId, "Submitted", parsedPrice, item);
    }

    @Override
    public String toString() {
        return "OrderForm{" +
                "userId='" + userId + '\'' +
                ", price='" + price + '\'' +
                ", item='" + item + '\'' +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
