package com.example.paymentservice.controllers;

import com.example.paymentservice.dtos.InitiatePaymentDto;
import com.example.paymentservice.services.PaymentService;
import com.example.paymentservice.services.ProductService;
import com.razorpay.RazorpayException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private PaymentService paymentService;
    private ProductService productService;

    public PaymentController(PaymentService paymentService, ProductService productService) {
        this.paymentService = paymentService;
        this.productService = productService;
    }

    @PostMapping("/link")
    public String initiatePayment(@RequestBody InitiatePaymentDto initiatePaymentDto) throws Exception {
        return paymentService.initiatePayment(initiatePaymentDto.getEmail(),
                initiatePaymentDto.getPhoneNumber(),
                initiatePaymentDto.getOrderId(),
                initiatePaymentDto.getAmount());
    }

    @PostMapping("/webhook")
    public String listenToWebhook(@RequestBody String webhookEvent) {
        System.out.println(webhookEvent);
        return "OK";
    }

    @GetMapping("/products/{id}")
    public String getProductDetails(@PathVariable("id") long id) throws Exception {
        return productService.getProductDetails(id);
    }
}
