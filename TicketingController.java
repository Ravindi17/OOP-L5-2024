package com.TicketingSystem.controller;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.read.CyclicBufferAppender;
import com.TicketingSystem.config.TicketingConfig;
import com.TicketingSystem.model.Customer;
import com.TicketingSystem.model.TicketPool;
import com.TicketingSystem.model.Vendor;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@RestController
@RequestMapping("/api/tickets")

@CrossOrigin(origins = "http://localhost:3000")
public class TicketingController {

	private static final Logger logger = LoggerFactory.getLogger(TicketingController.class);

	private TicketPool ticketPool;
	private ThreadPoolExecutor executor;
	private Vendor vendor;
	private Customer customer;
	private TicketingConfig config;

	@PostMapping("/config")
	public String setConfig(@RequestBody TicketingConfig ticketingConfig) {
		// Validate config
		if (ticketingConfig.getMaxTicketCapacity() < ticketingConfig.getTotalTickets()) {
			return "Error: maxTicketCapacity cannot be less than totalTickets";
		}

		this.config = ticketingConfig;
		return "Configuration set successfully!";
	}

	@PostMapping("/start")
	public String startTicketing() {
		if (config == null) {
			return "Error: Configuration not set!";
		}

		ticketPool = new TicketPool(config.getTotalTickets(), config.getMaxTicketCapacity());
		executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

		vendor = new Vendor(ticketPool, config.getTicketReleaseRate());
		customer = new Customer(ticketPool, config.getCustomerRetrievalRate());

		executor.execute(vendor);
		executor.execute(customer);

		logger.info("Ticketing system started with configuration: " + config);
		return "Ticketing system started!";
	}

	@PostMapping("/stop")
	public String stopTicketing() {
		if (executor != null && !executor.isShutdown()) {
			executor.shutdownNow();
			logger.info("Ticketing system stopped.");
			return "Ticketing system stopped!";
		}
		return "Error: Ticketing system is not running!";
	}

	@GetMapping("/status")
	public String getStatus() {
		if (ticketPool == null) {
			return "Ticketing system not started.";
		}
		return "Tickets available: " + ticketPool.getTicketCount();
	}

	@GetMapping("/logs")
	public String getLogs() {
		LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
		CyclicBufferAppender<?> appender = (CyclicBufferAppender<?>) context.getLogger("ROOT").getAppender("MEMORY");

		if (appender == null) {
			return "No in-memory log appender configured.";
		}

		StringBuilder logs = new StringBuilder("Recent Logs:\n");
		for (int i = 0; i < appender.getLength(); i++) {
			logs.append(appender.get(i)).append("\n");
		}

		return logs.toString();
	}
}
