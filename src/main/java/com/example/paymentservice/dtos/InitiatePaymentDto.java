package com.example.paymentservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


public class InitiatePaymentDto {
    private String orderId;
    private String email;
    private String phoneNumber;
    private Long amount;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
