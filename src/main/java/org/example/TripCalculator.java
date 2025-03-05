package org.example;

public class TripCalculator {
    private static final double FUEL_CONSUMPTION_RATE = 5.0;
    private static final double FUEL_PER_KILOMETER = FUEL_CONSUMPTION_RATE / 100;

    public double calculateTripCost(double distance, double fuelPrice) {
        if (distance <= 0) {
            throw new RuntimeException("Entered invalid distance (should be greater than 0)!");
        } else if (fuelPrice <= 0) {
            throw new RuntimeException("Entered invalid fuel price (should be greater than 0)!");
        }
        double fuelNeeded = distance * FUEL_PER_KILOMETER;
        return fuelNeeded * fuelPrice;
    }
}
