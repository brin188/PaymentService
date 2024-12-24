package com.example.paymentservice.paymentgateways;

import com.cashfree.*;
import com.cashfree.model.*;
import okhttp3.*;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class CashFreeGateway implements PaymentGateway {

    private Cashfree cashfree;
    private String xApiVersion = "2022-09-01";

    public CashFreeGateway(Cashfree cashfree) {
        this.cashfree = cashfree;
    }

    @Override
    public String generatePaymentLink(String email, String phoneNumber, String orderId, Long amount)
            throws Exception {

        OkHttpClient client = new OkHttpClient().newBuilder().build();

        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("link_id", "ps_link_123");
        paymentLinkRequest.put("link_amount", String.valueOf(amount));
        paymentLinkRequest.put("link_currency", "INR");
        paymentLinkRequest.put("link_purpose", "test");
        paymentLinkRequest.put("link_partial_payments", true);
        JSONObject customerDetails = new JSONObject();
        customerDetails.put("customer_phone", phoneNumber);
        customerDetails.put("customer_email", email);
        paymentLinkRequest.put("customer_details", customerDetails);
        JSONObject linkNotify = new JSONObject();
        linkNotify.put("send_sms", true);
        linkNotify.put("send_email", true);
        paymentLinkRequest.put("link_notify", linkNotify);
        JSONObject linkMeta = new JSONObject();
        linkMeta.put("return_url", "https://www.scaler.com");
        paymentLinkRequest.put("link_meta", linkMeta);


        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), paymentLinkRequest.toString());
        Request request = new Request.Builder()
                .url("https://sandbox.cashfree.com/pg/links")
                .addHeader("x-client-id", "TEST10383144defe305929f9bf1480c144138301")
                .addHeader(("x-client-secret"), "cfsk_ma_test_e559bb5b3f4b9fd8101db2885ea2a54e_3740a40d")
                .post(body)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response.body().string());
        return "success";
//        LinkCustomerDetailsEntity customerDetails = new LinkCustomerDetailsEntity();
//        customerDetails.setCustomerEmail(email);
//        customerDetails.setCustomerPhone(phoneNumber);
//        customerDetails.setCustomerName("TestCustomer");
//
//        LinkNotifyEntity linkNotify = new LinkNotifyEntity();
//        linkNotify.sendEmail(true);
//        linkNotify.sendSms(true);
//
//        CreateLinkRequest request = new CreateLinkRequest();
//        request.setCustomerDetails(customerDetails);
//        request.setLinkAmount(Double.valueOf(amount));
//        request.setLinkCurrency("INR");
//        request.setLinkPartialPayments(true);
//        request.setLinkNotify(linkNotify);
//
//        ApiCallback callback = new
//
//        try {
//            Response resp = cashfree.pGCreateLinkCall(xApiVersion, request, orderId, null, callback).execute();
//            //ApiResponse<OrderEntity> response = cashfree.PGCreateOrder(xApiVersion, request, null, null, null);
//            System.out.println(resp.body().string());
//        } catch (ApiException e) {
//            throw new RuntimeException(e);
//        }
    }
}
