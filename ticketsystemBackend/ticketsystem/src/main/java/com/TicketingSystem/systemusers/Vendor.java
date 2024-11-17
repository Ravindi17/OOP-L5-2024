package com.TicketingSystem.systemusers;

import com.TicketingSystem.TicketPool;
import com.TicketingSystem.config.Configuration;

public class Vendor implements Runnable{
    private TicketPool ticketpool;
    private Configuration configuration;
    private String vendorID;
    private int ticketsPerRelease;
    private int ticketsReleaseInterval ;

    public Vendor(String vendorID, int ticketsPerRelease , int ticketsReleaseInterval){
        this.vendorID = vendorID;
        this.ticketsReleaseInterval = ticketsReleaseInterval;
        this.ticketsPerRelease = ticketsPerRelease;
    }

    @Override
    public void run(){
        try{
            while (true){
                synchronized (ticketpool){
                    if(ticketpool.getTotalNumTickets() < configuration.getMaximumTicketCapacity()){
                        int ticketsAddedNew = Math.min(ticketsPerRelease , configuration.getMaximumTicketCapacity() - ticketpool. getTotalNumTickets());
                        ticketpool.addTickets(ticketsAddedNew);
                        System.out.println("The vendor with ID" + vendorID + "has a release rate of " + ticketsPerRelease + "tickets.");
                    }else{
                        System.out.println("Vendor reached maximum capacity");
                    }
                }

                Thread.sleep(ticketsReleaseInterval);
            }
        }catch (InterruptedException e){
            System.out.println("The activities of vendor" + vendorID + "has stopped");

        }
    }

}
