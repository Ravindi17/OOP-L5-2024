package com.TicketingSystem.systemusers;

import com.TicketingSystem.TicketPool;
import com.TicketingSystem.config.Configuration;
import java.util.logging.Logger;

public class Vendor implements Runnable {
    private final TicketPool ticketpool;
    private final String vendorID;
    private final int ticketsPerRelease;
    private final int ticketsReleaseInterval;

    private static final Logger log = Logger.getLogger(Vendor.class.getName());

    public Vendor(String vendorID, int ticketsPerRelease, int ticketsReleaseInterval, TicketPool ticketpool) {
        this.vendorID = vendorID;
        this.ticketsReleaseInterval = ticketsReleaseInterval;
        this.ticketsPerRelease = ticketsPerRelease;
        this.ticketpool = ticketpool;
    }

    @Override
    public void run() {
        try {
            while (true) {
                ticketpool.addTicket(ticketsPerRelease);
                log.info("Vendor" + vendorID + "added" +ticketsPerRelease + " tickets");
                Thread.sleep(ticketsReleaseInterval);
            }
        }catch (InterruptedException e) {
            log.severe("Interrupted");
        }
    }

}


