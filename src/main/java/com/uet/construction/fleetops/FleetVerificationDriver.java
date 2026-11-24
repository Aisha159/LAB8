package com.uet.construction.fleetops;

import java.util.List;

public class FleetVerificationDriver {

    // Helper method for verification
    private static void check(boolean condition, String testName) {
        if (condition) {
            System.out.println("[PASS] " + testName);
        } else {
            System.out.println("[FAIL] " + testName);
            throw new AssertionError(testName);
        }
    }

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("      FLEETOPS VERIFICATION TESTS");
        System.out.println("========================================");

        // ==========================================
        // TEST CASE A: AGGREGATION LIFECYCLE
        // ==========================================

        System.out.println("\n--- Test Case A: Aggregation Independence ---");

        // Create Drivers independently
        Driver driver1 = new Driver("D01", "Ali Khan", "LIC-101");
        Driver driver2 = new Driver("D02", "Ahmed Shah", "LIC-202");

        // Create Vehicles independently
        Vehicle vehicle1 = new Vehicle(
                "VIN-001", "Toyota Corolla",
                "ENG-001", 1800, "Petrol"
        );

        Vehicle vehicle2 = new Vehicle(
                "VIN-002", "Honda Civic",
                "ENG-002", 1600, "Petrol"
        );

        // Assign independently created Driver objects
        vehicle1.assignDriver(driver1);
        vehicle2.assignDriver(driver2);

        // Create Depot
        FleetDepot depot = new FleetDepot("DEP-01", "Abbottabad");

        // Assign existing Vehicles to Depot
        depot.assignVehicle(vehicle1);
        depot.assignVehicle(vehicle2);

        System.out.println("Depot before release: " + depot);
        System.out.println("Vehicles assigned: " + depot.getAssignedVehicles().size());

        // Remove Depot reference
        depot = null;

        System.out.println("\nDepot reference set to null.");

        // Verify that Vehicles still exist
        check(vehicle1 != null, "Vehicle 1 still exists independently");
        check(vehicle2 != null, "Vehicle 2 still exists independently");

        // Verify Vehicles retain their data
        check(vehicle1.getVin().equals("VIN-001"),
                "Vehicle 1 retains its VIN");

        check(vehicle2.getModel().equals("Honda Civic"),
                "Vehicle 2 retains its model");

        // Verify Drivers still exist and are accessible
        check(vehicle1.getDriver() == driver1,
                "Vehicle 1 still references Driver 1");

        check(vehicle2.getDriver() == driver2,
                "Vehicle 2 still references Driver 2");

        System.out.println("\nVehicle 1: " + vehicle1);
        System.out.println("Vehicle 2: " + vehicle2);
        System.out.println("Driver 1: " + driver1);
        System.out.println("Driver 2: " + driver2);

        // ==========================================
        // TEST CASE B: COMPOSITION & ENCAPSULATION
        // ==========================================

        System.out.println("\n--- Test Case B: Composition & Encapsulation ---");

        // Create Vehicle with its internally created Engine
        Vehicle vehicle3 = new Vehicle(
                "VIN-003", "Suzuki Swift",
                "ENG-003", 1200, "Petrol"
        );

        // Add maintenance records through Vehicle
        vehicle3.addMaintenanceRecord("M01", "Oil Change", 5000);
        vehicle3.addMaintenanceRecord("M02", "Brake Inspection", 3000);

        // Get defensive copy of Engine
        Engine leakedEngine = vehicle3.getEngine();

        System.out.println("\nOriginal Engine Copy: " + leakedEngine);

        // Attempt to change the returned Engine copy
        leakedEngine.setEngineNumber("HACKED-ENGINE");
        leakedEngine.setDisplacementCC(9999);
        leakedEngine.setFuelType("Modified Fuel");

        // Fetch Engine again from Vehicle
        Engine actualEngine = vehicle3.getEngine();

        // Verify original Engine remains unchanged
        check(!actualEngine.getEngineNumber().equals("HACKED-ENGINE"),
                "External Engine modification does not affect internal Engine");

        check(actualEngine.getDisplacementCC() == 1200,
                "Internal Engine displacement remains unchanged");

        check(actualEngine.getFuelType().equals("Petrol"),
                "Internal Engine fuel type remains unchanged");

        System.out.println("Internal Engine: " + actualEngine);

        // Test maintenance history defensive copying
        List<MaintenanceLog> history = vehicle3.getMaintenanceHistory();

        System.out.println("\nMaintenance history before external changes:");
        for (MaintenanceLog log : history) {
            System.out.println(log);
        }

        // Attempt to clear the returned history list
        boolean clearBlocked = false;

        try {
            history.clear();
        } catch (UnsupportedOperationException e) {
            clearBlocked = true;
        }

        check(clearBlocked,
                "External caller cannot clear returned maintenance list");

        // Get a new copy of maintenance history
        List<MaintenanceLog> newHistory = vehicle3.getMaintenanceHistory();

        // Verify the internal history still has both records
        check(newHistory.size() == 2,
                "Internal maintenance history retains both records");

        // Attempt to mutate a returned maintenance record
        MaintenanceLog leakedLog = newHistory.get(0);
        leakedLog.setServiceDescription("Externally Modified");

        // Fetch history again
        List<MaintenanceLog> actualHistory = vehicle3.getMaintenanceHistory();

        check(!actualHistory.get(0).getServiceDescription()
                        .equals("Externally Modified"),
                "External record modification does not affect internal history");

        System.out.println("\nActual maintenance history:");
        for (MaintenanceLog log : actualHistory) {
            System.out.println(log);
        }

        // Test Depot's unmodifiable vehicle list
        FleetDepot depot2 = new FleetDepot("DEP-02", "Islamabad");
        depot2.assignVehicle(vehicle3);

        boolean depotListProtected = false;

        try {
            depot2.getAssignedVehicles().clear();
        } catch (UnsupportedOperationException e) {
            depotListProtected = true;
        }

        check(depotListProtected,
                "External caller cannot modify Depot's vehicle list");

        System.out.println("\n========================================");
        System.out.println(" ALL VERIFICATION TESTS PASSED!");
        System.out.println("========================================");
    }
}