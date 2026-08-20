package com.sarvu.bigkart.controller;

import com.sarvu.bigkart.KafkaConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomerController {

    @Autowired
    private KafkaConsumerService kafkaConsumerService;

    @GetMapping("/")
    public String showIndexPage() {
        return "index";
    }

    @GetMapping("/kafka-test")
    public String showCustomerPage() {
        return "customer-kafka";
    }

    @GetMapping("/latest-number")
    @ResponseBody
    public String getLatestNumber() {
        return String.valueOf(kafkaConsumerService.getCurrentNumber());
    }
}
