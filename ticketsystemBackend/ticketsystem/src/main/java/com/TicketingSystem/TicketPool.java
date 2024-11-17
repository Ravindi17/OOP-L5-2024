package com.TicketingSystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.TicketingSystem.systemusers.Ticket;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Queue;

public class TicketPool {
    private final Queue<Ticket> tickets = new ConcurrentLinkedQueue<>();
    private final int maximumCapacity;
    private int nextTicketID = 1;

    public TicketPool(int maximumCapacity){
        this.maximumCapacity = maximumCapacity;
    }

    private static final Logger logger = LoggerFactory.getLogger(TicketPool.class);

    public synchronized void addTickets(int numberOfTickets){
        for (int num = 0; num < numberOfTickets; num++){
            if(tickets.size() < maximumCapacity){
                Ticket newTicket = new Ticket(nextTicketID);
                tickets.add(newTicket);
                logger.info("The ticket " + newTicket+ "has been added succesfully. The total ticket number is " + tickets.size() );
                nextTicketID++;
            }
            else{
                logger.warn("The maximum number of tickets released. Cannot add the ticket");
                break;
            }
        }
    }
    public synchronized Ticket removeTicket(){
        if(!tickets.isEmpty()){
            Ticket ticket = tickets.poll();
            logger.info("Ticket" + ticket + "removed succesfully. The number of tickets available are" + tickets.size());
            return ticket;
        }else{
          logger.warn("No tickets available to be removed");
          return null;
        }
    }

    public synchronized int getTotalNumTickets(){
        return  tickets.size();
    }

}
