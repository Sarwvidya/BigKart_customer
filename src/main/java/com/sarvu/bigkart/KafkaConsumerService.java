package com.sarvu.bigkart;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private int currentNumber = 0;

    @KafkaListener(topics = "increment-topic", groupId = "bigkart-customer-group")
    public void consumeNumber(String message) {
        try {
            this.currentNumber = Integer.parseInt(message);
        } catch (NumberFormatException e) {
            System.err.println("Received invalid number format: " + message);
        }
    }

    public int getCurrentNumber() {
        return currentNumber;
    }
}
