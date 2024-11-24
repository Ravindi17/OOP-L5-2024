package com.TicketingSystem;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Logger;

public class TicketPool {
    private final Queue<Integer> tickets = new LinkedList<>();
    private final int maxCapacity;
    private int ticketID = 0;

    private static final Logger logger = Logger.getLogger(TicketPool.class.getName());

    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public synchronized void addTicket(int numberOfTickets) {
            for(int num = 0; num < numberOfTickets; num++) {
                if(tickets.size() < maxCapacity) {
                    tickets.add(ticketID++);
                    logger.info("Ticket with ID: "+ ticketID+ " added succesfully: " + "The total number of tickets now : " + tickets.size());
                }else {
                    logger.warning("Ticket capacity is reached.");
                    break;
                }
            }
           notifyAll();
    }

    public synchronized Integer removeTicket() {
        if (tickets.isEmpty()) {
            logger.warning("No tickets available to remove.");
            return null; // Explicitly indicate no ticket was removed
        }
        Integer ticket = tickets.poll();
        logger.info("Ticket removed successfully: " + ticket + ". Remaining tickets: " + tickets.size());
        return ticket;
    }


    public synchronized int getCount() {
        return tickets.size();
    }




}
