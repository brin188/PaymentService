package com.example.paymentservice.paymentgateways;

import com.razorpay.RazorpayException;

public interface PaymentGateway {

    public String generatePaymentLink(String email, String phoneNumber, String orderId, Long amount)
            throws Exception;
}
