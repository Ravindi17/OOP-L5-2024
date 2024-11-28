package com.TicketingSystem;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class TicketPool {
    private final List<Integer> tickets = new LinkedList<>();
    private final int maxCapacity;
    private int ticketIDNumber = 1;

    private static final Logger logger = Logger.getLogger(TicketPool.class.getName());

    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public synchronized boolean addTicket() {
                if(tickets.size() >= maxCapacity) {
                    logger.warning("Ticket with ID: "+ ticketIDNumber + " " + "cannot be added. Maximum reached " );
                    return false;
                }
                tickets.add(ticketIDNumber);
                logger.info(Thread.currentThread().getName() + " added ticket with ID" + " " + ticketIDNumber + " " + " to the pool");
                ticketIDNumber++;
                return true;
    }

    public synchronized Integer removeTicket() {
        if (tickets.isEmpty()) {
            try{
                logger.warning(Thread.currentThread().getName()+ " " +"No tickets available to remove.");
                wait(); // Explicitly indicate no ticket was removed
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
            return null;
        }
        Integer ticket = tickets.remove(0);
        logger.info("Ticket " + ticket + " " +  "removed successfully" + " " + "Remaining tickets in the pool:" +  " " + tickets.size());
        return ticket;
    }


    public synchronized int getCount() {
        return tickets.size();
    }

    public synchronized void notifyAllThreads(){
        notifyAll();
    }
}
