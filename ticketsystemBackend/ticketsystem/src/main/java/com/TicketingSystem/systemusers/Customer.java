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
        try{
            while(true){
                Integer ticket = null;
                synchronized (ticketPool){
                    ticket = ticketPool.removeTicket();
                    if(ticket == null){
                        logger.warning("customer has no ticket to purchase");
                        ticketPool.wait();
                    }
                }
                if(ticket != null){
                   logger.info("customer "+ customerID +"purchased ticket: " + ticket);
                }

                Thread.sleep(customerRetrievalRate);
                }

        }catch (InterruptedException e){
            logger.severe("The activities of customer " + customerID + "has stopped");
            Thread.currentThread().interrupt();
        }


    }
}
