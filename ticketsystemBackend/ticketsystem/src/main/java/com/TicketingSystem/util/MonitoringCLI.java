package com.TicketingSystem.util;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MonitoringCLI {

    private static final String POOL_STATUS_URL = "http://localhost:8080/api/tickets/status";
    private final RestTemplate restTemplate = new RestTemplate();

    public void startMonitoring() {
        System.out.println("Monitoring ticket pool status...");
        while (true) {
            try {
                String status = restTemplate.getForObject(POOL_STATUS_URL, String.class);
                System.out.println("Current Pool Status: " + status);
                Thread.sleep(5000); // Poll every 5 seconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}

