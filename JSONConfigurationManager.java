package com.ticketing;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JSONConfigurationManager {

    public static void saveConfigurationToJSON(Configuration config, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            Gson gson = new Gson();
            gson.toJson(config, writer);
            System.out.println("Configuration saved to JSON successfully!");
        } catch (IOException e) {
            System.out.println("Error saving configuration to JSON: " + e.getMessage());
        }
    }

    public static Configuration loadConfigurationFromJSON(String filename) {
        try (FileReader reader = new FileReader(filename)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, Configuration.class);
        } catch (IOException e) {
            System.out.println("Error loading configuration from JSON: " + e.getMessage());
            return null;
        }
    }
}
