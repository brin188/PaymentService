package com.example.paymentservice.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {

    private RestTemplate restTemplate;

    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getProductDetails(long id) {
        return restTemplate.getForObject("http://SPRINGAPPDEMO/products/" + id, String.class);
    }
}
