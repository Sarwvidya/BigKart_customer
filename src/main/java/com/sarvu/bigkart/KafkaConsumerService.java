package com.sarvu.bigkart;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private int currentNumber = 0;

    @KafkaListener(topics = "increment-topic", groupId = "bigkart-customer-group")
    public void consumeNumber(int message) {
        this.currentNumber = message;
    }

    public int getCurrentNumber() {
        return currentNumber;
    }
}
