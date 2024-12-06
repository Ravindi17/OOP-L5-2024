package com.TicketingSystem.systemusers;

import com.TicketingSystem.TicketPool;
import java.util.logging.Logger;

public class Customer implements Runnable {
    private String customerId;
    private int customerRetrievalRate;
    private TicketPool ticketPool;
    private volatile boolean running = true;

    private static final Logger logger = Logger.getLogger(Customer.class.getName());

    public Customer(String customerId, int customerRetrievalRate, TicketPool ticketPool) {
        this.customerId = customerId;
        this.customerRetrievalRate = customerRetrievalRate;
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        while (running) {
            try {
                // Simulate customer retrieval rate
                Thread.sleep(customerRetrievalRate);

                // Retrieve a ticket from the pool
                Integer ticket = ticketPool.removeTicket();
                if (ticket != null) {
                    logger.info("Customer " + customerId + " retrieved ticket with ID: " + ticket);
                }
            } catch (InterruptedException e) {
                // Handle interruption
                logger.warning("Customer " + customerId + " thread interrupted.");
                break;
            }
        }
    }

    public void stopRunning() {
        running = false;
    }
}
