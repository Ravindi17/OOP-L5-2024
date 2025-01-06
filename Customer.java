package com.TicketingSystem.model;


import java.util.concurrent.TimeUnit;

public class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final int purchaseRate;

    public Customer(TicketPool ticketPool, int purchaseRate) {
        this.ticketPool = ticketPool;
        this.purchaseRate = purchaseRate;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Integer ticket = ticketPool.removeTicket();
                if (ticket != null) {
                    // Successfully purchased a ticket
                } else {
                    // No tickets available
                }
                TimeUnit.SECONDS.sleep(1); // Simulating the time interval for purchasing tickets
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
