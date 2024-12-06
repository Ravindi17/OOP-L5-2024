package com.TicketingSystem;


import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class TicketPool {
    private final List<Integer> tickets = new LinkedList<>();
    private final int maxCapacity;
    private int ticketIDNumber = 1;
    private boolean isFull = false;

    private static final Logger logger = Logger.getLogger(TicketPool.class.getName());


    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getTicketIDNumber() {
        return ticketIDNumber;
    }

    public void setTicketIDNumber(int ticketIDNumber) {
        this.ticketIDNumber = ticketIDNumber;
    }

    public  synchronized Boolean  addTicket() {
        if(tickets.size() > maxCapacity) {
            if(!isFull) {
                isFull = true;
                logger.warning("Ticket with ID: "+ ticketIDNumber + " " + "cannot be added. Maximum reached " );
            }
            return false;

        }else{
            tickets.add(ticketIDNumber);
            logger.info(" New ticket with ID" + " " + ticketIDNumber + " " + " to the pool");
            ticketIDNumber++;
            return true;

        }
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
        Integer ticket = tickets.removeFirst();
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
