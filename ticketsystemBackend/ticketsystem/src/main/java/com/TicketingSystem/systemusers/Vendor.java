package com.TicketingSystem.systemusers;

import com.TicketingSystem.TicketPool;
import java.util.logging.Logger;

public class Vendor implements Runnable {
    private final String vendorId;
    private final int ticketReleaseRate;
    private final TicketPool ticketPool;
    private volatile boolean running = true;

    private static final Logger logger = Logger.getLogger(Vendor.class.getName());

    public Vendor(String vendorId, int ticketReleaseRate, TicketPool ticketPool) {
        this.vendorId = vendorId;
        this.ticketReleaseRate = ticketReleaseRate;
        this.ticketPool = ticketPool;
    }


    @Override
    public void run() {
        while (running) {
            try {
                // Simulate ticket release rate
                Thread.sleep(ticketReleaseRate);

                // Add a ticket to the pool
                boolean added = ticketPool.addTicket();

                if (added) {
                    logger.info("Ticket with ID: " + ticketPool.getTicketIDNumber()+ "added by : " + vendorId );
                }
            } catch (InterruptedException e) {
                // Handle interruption
                logger.warning("Vendor " + vendorId + " thread interrupted.");
                break;
            }
        }
    }

    public void stopRunning() {
        running = false;
    }
}
