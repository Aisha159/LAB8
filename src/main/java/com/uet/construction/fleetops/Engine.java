package com.uet.construction.fleetops;

public class Engine {

    private String engineNumber;
    private int displacementCC;
    private String fuelType;

    // Parameterized constructor
    public Engine(String engineNumber, int displacementCC, String fuelType) {
        this.engineNumber = engineNumber;
        this.displacementCC = displacementCC;
        this.fuelType = fuelType;
    }

    // Copy constructor
    public Engine(Engine other) {
        this.engineNumber = other.engineNumber;
        this.displacementCC = other.displacementCC;
        this.fuelType = other.fuelType;
    }

    // Getters
    public String getEngineNumber() {
        return engineNumber;
    }

    public int getDisplacementCC() {
        return displacementCC;
    }

    public String getFuelType() {
        return fuelType;
    }

    // Setters (needed to demonstrate defensive copying)
    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public void setDisplacementCC(int displacementCC) {
        this.displacementCC = displacementCC;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "engineNumber='" + engineNumber + '\'' +
                ", displacementCC=" + displacementCC +
                ", fuelType='" + fuelType + '\'' +
                '}';
    }
}