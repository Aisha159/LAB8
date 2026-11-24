package com.uet.construction.fleetops;

public class Driver {

    private String driverId;
    private String fullName;
    private String licenseNumber;

    public Driver(String driverId, String fullName, String licenseNumber) {
        setDriverId(driverId);
        setFullName(fullName);
        setLicenseNumber(licenseNumber);
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        if (driverId == null || driverId.isBlank()) {
            throw new IllegalArgumentException("Driver ID cannot be empty");
        }
        this.driverId = driverId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Full name cannot be empty");
        }
        this.fullName = fullName;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        if (licenseNumber == null || licenseNumber.isBlank()) {
            throw new IllegalArgumentException("License number cannot be empty");
        }
        this.licenseNumber = licenseNumber;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "driverId='" + driverId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                '}';
    }
}