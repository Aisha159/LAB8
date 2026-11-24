package com.uet.construction.fleetops;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FleetDepot {

    private String depotId;
    private String locationName;
    private List<Vehicle> assignedVehicles;

    // Constructor
    public FleetDepot(String depotId, String locationName) {
        this.depotId = depotId;
        this.locationName = locationName;
        this.assignedVehicles = new ArrayList<>();
    }

    // Assign an existing Vehicle (Aggregation)
    public void assignVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }

        if (!assignedVehicles.contains(vehicle)) {
            assignedVehicles.add(vehicle);
        }
    }

    // Release Vehicle without destroying it
    public void releaseVehicle(Vehicle vehicle) {
        assignedVehicles.remove(vehicle);
    }

    // Return unmodifiable view
    public List<Vehicle> getAssignedVehicles() {
        return Collections.unmodifiableList(assignedVehicles);
    }

    public String getDepotId() {
        return depotId;
    }

    public String getLocationName() {
        return locationName;
    }

    @Override
    public String toString() {
        return "FleetDepot{" +
                "depotId='" + depotId + '\'' +
                ", locationName='" + locationName + '\'' +
                ", assignedVehicles=" + assignedVehicles.size() +
                '}';
    }
}