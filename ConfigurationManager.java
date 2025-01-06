package com.ticketing;

import java.io.*;

public class ConfigurationManager {

    public static void saveConfiguration(Configuration config, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(config);
            System.out.println("Configuration saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving configuration: " + e.getMessage());
        }
    }

    public static Configuration loadConfiguration(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (Configuration) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading configuration: " + e.getMessage());
            return null;
        }
    }
}
