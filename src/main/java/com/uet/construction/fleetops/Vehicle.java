package com.uet.construction.fleetops;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Vehicle {

    private String vin;
    private String model;
    private Engine engine;
    private List<MaintenanceLog> maintenanceHistory;
    private Driver driver;

    // Constructor creates its own Engine (Composition)
    public Vehicle(String vin, String model,
                  String engineNumber, int displacementCC, String fuelType) {

        this.vin = vin;
        this.model = model;

        // Engine is created internally
        this.engine = new Engine(engineNumber, displacementCC, fuelType);

        // Internal maintenance list
        this.maintenanceHistory = new ArrayList<>();

        // Initially no driver assigned
        this.driver = null;
    }

    // Getters for vehicle information
    public String getVin() {
        return vin;
    }

    public String getModel() {
        return model;
    }

    // Defensive copy of Engine
    public Engine getEngine() {
        return new Engine(this.engine);
    }

    // Add maintenance record internally
    public void addMaintenanceRecord(String logId, String description, double cost) {
        MaintenanceLog log = new MaintenanceLog(logId, description, cost);
        maintenanceHistory.add(log);
    }

    // Return deep copies of maintenance records
    public List<MaintenanceLog> getMaintenanceHistory() {

        List<MaintenanceLog> copiedHistory = new ArrayList<>();

        for (MaintenanceLog log : maintenanceHistory) {
            copiedHistory.add(new MaintenanceLog(log));
        }

        return Collections.unmodifiableList(copiedHistory);
    }

    // Aggregation: assign an independently existing Driver
    public void assignDriver(Driver driver) {
        this.driver = driver;
    }

    // Release Driver without destroying Driver object
    public void releaseDriver() {
        this.driver = null;
    }

    public Driver getDriver() {
        return driver;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vin='" + vin + '\'' +
                ", model='" + model + '\'' +
                ", engine=" + engine +
                ", driver=" + driver +
                '}';
    }
}