package com.TicketingSystem.systemusers;

import com.TicketingSystem.TicketPool;
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
        Thread.currentThread().setName("Vendor ID: " + vendorID);
        try {
            while (true) {
                synchronized (ticketpool) {
                    int addedTicketsNum = 0;

                    for (int num = 0; num < ticketsPerRelease; num++) {
                        int ticketID = ticketpool.getCount() + 1;
                        if(ticketpool.addTicket()){
                            addedTicketsNum++;
                        }else{
                            log.warning(Thread.currentThread().getName() + " " + "Unsuccessful in adding ticket with ID " + ticketID);
                            break;
                        }
                    }
                    log.info(Thread.currentThread().getName() + " " +  "Added" + " " +  addedTicketsNum + " " + "tickets");
                    ticketpool.notifyAll();
                }
                Thread.sleep(ticketsReleaseInterval);
            }
        }catch(InterruptedException e){
            log.severe(Thread.currentThread().getName()+ " " + "Thread interrupted");
            Thread.currentThread().interrupt();
        }
    }
}