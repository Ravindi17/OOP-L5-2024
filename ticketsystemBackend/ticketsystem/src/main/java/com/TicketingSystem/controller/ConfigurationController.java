/*package com.TicketingSystem.controller;

import com.TicketingSystem.model.Configuration;
import com.TicketingSystem.service.ConfigurationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/configurations")
public class ConfigurationController {

    private final ConfigurationService service;

    public ConfigurationController(ConfigurationService service) {
        this.service = service;
    }

    @GetMapping("/getconfiguration")
    public ResponseEntity<Configuration> getConfiguration() {
        return service.getConfiguration()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).build());
    }

    @PostMapping
    public ResponseEntity<String> saveConfiguration(@RequestBody Configuration config) {
        service.saveConfiguration(config);
        return ResponseEntity.ok("Configuration saved successfully.");
    }
}
*/

package com.TicketingSystem.controller;

import com.TicketingSystem.model.Configuration;
import com.TicketingSystem.service.ConfigurationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/configuration")
public class ConfigurationController {

    private final ConfigurationService service;

    public ConfigurationController(ConfigurationService service) {
        this.service = service;
    }

    @GetMapping("/getting")
    public ResponseEntity<Configuration> getConfiguration() {
        Optional<Configuration> config = service.getConfiguration();
        if (config.isPresent()) {
            return ResponseEntity.ok(config.get());
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PostMapping("/saving")
    public ResponseEntity<String> saveConfiguration(@RequestBody Configuration config) {
        try {
            service.saveConfiguration(config);
            return ResponseEntity.ok("Configuration saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error saving configuration: " + e.getMessage());
        }
    }
}

