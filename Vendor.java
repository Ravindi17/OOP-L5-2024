package com.TicketingSystem.model;

import java.util.concurrent.TimeUnit;

public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final int releaseRate;

    public Vendor(TicketPool ticketPool, int releaseRate) {
        this.ticketPool = ticketPool;
        this.releaseRate = releaseRate;
    }

    @Override
    public void run() {
        while (true) {
            try {
                ticketPool.addTickets(releaseRate);
                TimeUnit.SECONDS.sleep(1); // Simulating the time interval for adding tickets
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
