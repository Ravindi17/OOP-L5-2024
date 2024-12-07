package com.TicketingSystem.service;

import com.TicketingSystem.model.Configuration;
import com.TicketingSystem.repository.ConfigurationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConfigurationService {

    private final ConfigurationRepository repository;

    public ConfigurationService(ConfigurationRepository repository) {
        this.repository = repository;
    }

    public Optional<Configuration> getConfiguration() {
        return repository.findById(1L); // Fetch the first configuration (assumes a single configuration)
    }

    public Configuration saveConfiguration(Configuration config) {
        return repository.save(config);
    }
}
