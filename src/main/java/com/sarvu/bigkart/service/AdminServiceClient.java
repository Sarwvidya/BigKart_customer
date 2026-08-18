package com.sarvu.bigkart.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AdminServiceClient {

    private static final Logger logger = LoggerFactory.getLogger(AdminServiceClient.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${bigkart.admin.url}")
    private String adminUrl;

    @CircuitBreaker(name = "adminService", fallbackMethod = "adminServiceFallback")
    public String getAdminHello() {
        String url = adminUrl + "/hello";
        logger.info("Calling Admin Service at: {}", url);
        return restTemplate.getForObject(url, String.class);
    }

    public String adminServiceFallback(Exception e) {
        logger.error("Admin Service call failed. Circuit Breaker Fallback executed. Exception: {}", e.getMessage());
        return "Admin service is currently down. Please try again later. (Fallback Response)";
    }
}
