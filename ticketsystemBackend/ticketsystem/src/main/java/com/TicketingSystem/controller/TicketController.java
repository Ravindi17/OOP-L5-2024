package com.TicketingSystem.controller;/*package com.TicketingSystem.controller;

import com.TicketingSystem.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;


    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addTicket() {
        try {
            ticketService.addTicket(); // Call TicketService's addTicket method
            return ResponseEntity.ok("Ticket added successfully.");
        } catch (InterruptedException e) {
            return ResponseEntity.status(500).body("Error adding ticket: " + e.getMessage());
        }
    }

    @PostMapping("/remove")
    public ResponseEntity<String> removeTicket() {
        try {
            int ticketId = ticketService.removeTicket(); // Call TicketService's removeTicket method
            return ResponseEntity.ok("Ticket removed successfully. Ticket ID: " + ticketId);
        } catch (InterruptedException e) {
            return ResponseEntity.status(500).body("Error removing ticket: " + e.getMessage());
        }
    }

    @GetMapping("/status")
    public ResponseEntity<String> getTicketPoolStatus() {
        return ResponseEntity.ok(ticketService.getTicketPoolStatus()); // Call TicketService's getTicketPoolStatus method
    }
}
*/


import com.TicketingSystem.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/start")
    public ResponseEntity<String> startTicketSystem() {
        ticketService.startSystem();
        return ResponseEntity.ok("Ticket system started.");
    }

    @PostMapping("/stop")
    public ResponseEntity<String> stopTicketSystem() {
        ticketService.stopSystem();
        return ResponseEntity.ok("Ticket system stopped.");
    }

    @GetMapping("/summary")
    public ResponseEntity<Map<String, Integer>> getSummary() {
        return ResponseEntity.ok(ticketService.getSummary());
    }
}
