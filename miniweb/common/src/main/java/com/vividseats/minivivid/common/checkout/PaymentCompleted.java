package com.vividseats.minivivid.common.checkout;

@SuppressWarnings("unused")
public class PaymentCompleted {

    private String PaymentId;
    private String OrderId;

    public PaymentCompleted() {
    }

    public PaymentCompleted(String paymentId, String orderId) {
        PaymentId = paymentId;
        OrderId = orderId;
    }

    @Override
    public String toString() {
        return "PaymentCompleted{" +
                "PaymentId=" + PaymentId +
                ", OrderId=" + OrderId +
                '}';
    }

    public String getPaymentId() {
        return PaymentId;
    }

    public void setPaymentId(String paymentId) {
        PaymentId = paymentId;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }
}
