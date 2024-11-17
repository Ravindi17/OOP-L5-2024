package com.TicketingSystem;

import java.lang.module.Configuration;

public class TicketHandler {
    private final TicketPool ticketPool;
    private final Configuration configuration;
    private Thread releaseThread;
    private Thread retrievalThread;
    private boolean running = false;

    public TicketHandler(TicketPool ticketPool, Configuration configuration) {
        this.ticketPool = ticketPool;
        this.configuration = configuration;
    }

    public void start() {
        if (running){
            System.out.println("Ticket Handler is already running");
            return;
        }
        running = true;
    }
    public void stop() {
        if(!running){
            System.out.println("Ticket Handler is already stopped");
        }
        running = false;
    }
}
