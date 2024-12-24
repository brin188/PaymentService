package com.example.paymentservice.configs;

import com.cashfree.Cashfree;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SpringBeansConfig {

    @Value("${razorpay.key.secret}")
    private String secret;

    @Value("${razorpay.key.id}")
    private String id;

    @Bean
    public RazorpayClient getRazorpayClient() throws RazorpayException {
        return new RazorpayClient(id, secret);
    }

    @Bean
    public Cashfree getCashfree() {
        return new Cashfree();
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {

        return new RestTemplate(); // new RestTemplateBuilder().build();
    }

}
