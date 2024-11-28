package com.TicketingSystem.systemusers;

import com.TicketingSystem.TicketPool;
import java.util.logging.Logger;


public class Customer implements Runnable {
    private final String customerID;
    private final int customerRetrievalRate;
    private final TicketPool ticketPool;

    public static final  Logger logger = Logger.getLogger(Customer.class.getName());

    public Customer(String customerID , int customerRetrievalRate , TicketPool ticketPool){
        this.customerID = customerID;
        this.customerRetrievalRate = customerRetrievalRate;
        this.ticketPool = ticketPool;
    }

    @Override
    public void run(){
        Thread.currentThread().setName("Customer ID: " + customerID);
        try{
            while(true){
                Integer ticket = null;

                synchronized (ticketPool){
                    while(ticketPool.getCount() == 0){
                        logger.warning(Thread.currentThread().getName() + " "+ "customer has no ticket to purchase");
                        ticketPool.wait();
                    }
                    ticket = ticketPool.removeTicket();
                    logger.info(Thread.currentThread().getName() + " "+ "purchased ticket: " + ticket);
                    ticketPool.notify();
                }

                    Thread.sleep(customerRetrievalRate);
                }

        }catch (InterruptedException e){
            logger.severe(Thread.currentThread().getName() + "The activities of customer" + " " + customerID + "has stopped");
            Thread.currentThread().interrupt();
        }
    }
}
