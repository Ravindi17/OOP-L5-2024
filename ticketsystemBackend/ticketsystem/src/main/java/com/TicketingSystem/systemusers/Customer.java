package com.TicketingSystem.systemusers;

import com.TicketingSystem.TicketPool;

public class Customer implements Runnable {
    private String customerID;
    private int customerRetrievalRate;
    private TicketPool ticketPool;

    public Customer(String customerID , int customerRetrievalRate){
        this.customerID = customerID;
        this.customerRetrievalRate = customerRetrievalRate;
        this.ticketPool = ticketPool;
    }


    public void run(){
        try{
            while(true){
                Ticket ticket = ticketPool.removeTicket();
                if(ticket != null){
                    System.out.println("customer "+ customerID +"purchased ticket: " + ticket);
                }
                else{
                    System.out.println("Customer" + customerID+ " has no tickets to purchase");
                }
                Thread.sleep(customerRetrievalRate);
            }


        }catch (InterruptedException e){
            System.out.println("The activities of customer " + customerID + "has stopped");
        }


    }
}
