package com.example.paymentservice.paymentgateways;


import com.razorpay.PaymentLink;
import org.json.JSONObject;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.stereotype.Component;


public class RazorPayGateway implements PaymentGateway {

    private RazorpayClient razorpayClient;

    public RazorPayGateway(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    @Override
    public String generatePaymentLink(String email, String phoneNumber, String orderId, Long amount) throws RazorpayException {
        // call RazorPay API..refer Nikhil's code base
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",amount);
        paymentLinkRequest.put("currency","INR");
        paymentLinkRequest.put("accept_partial",true);
        paymentLinkRequest.put("first_min_partial_amount",100);
        paymentLinkRequest.put("expire_by",System.currentTimeMillis() + 36000);
        paymentLinkRequest.put("reference_id",orderId);
        paymentLinkRequest.put("description","Test payment 1");
        JSONObject customer = new JSONObject();
        customer.put("name","S Brindha");
        customer.put("contact",phoneNumber);
        customer.put("email",email);
        paymentLinkRequest.put("customer",customer);
        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("notify",notify);
        paymentLinkRequest.put("reminder_enable",true);
        JSONObject notes = new JSONObject();
        notes.put("policy_name","Jeevan Bima");
        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://www.scaler.com");
        paymentLinkRequest.put("callback_method","get");

        PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);
        return payment.get("short_url").toString();
    }
}
