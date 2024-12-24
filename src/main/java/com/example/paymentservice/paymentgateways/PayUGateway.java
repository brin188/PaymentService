package com.example.paymentservice.paymentgateways;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class PayUGateway implements PaymentGateway {
    @Override
    public String generatePaymentLink(String email, String phoneNumber, String orderId, Long amount)
            throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://uatoneapi.payu.in/payment-links")
                .post(null)
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        return "";
    }
}
