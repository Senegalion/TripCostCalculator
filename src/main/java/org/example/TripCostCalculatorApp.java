package org.example;

import java.util.Locale;
import java.util.Scanner;

public class TripCostCalculatorApp {
    public static final String DISTANCE_TO_TRAVEL_IN_KM_MESSAGE = "distance to travel (in km): ";
    public static final String FUEL_PRICE_PER_LITER_MESSAGE = "fuel price per liter: ";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        double distance = getInformationFromUser(DISTANCE_TO_TRAVEL_IN_KM_MESSAGE, scanner);

        double fuelPrice = getInformationFromUser(FUEL_PRICE_PER_LITER_MESSAGE, scanner);

        calculateFinalTripCost(distance, fuelPrice);

        scanner.close();
    }

    private static void calculateFinalTripCost(double distance, double fuelPrice) {
        TripCalculator tripCalculator = new TripCalculator();
        double totalCost = tripCalculator.calculateTripCost(distance, fuelPrice);
        System.out.printf("Total trip cost: %.2f%n", totalCost);
    }

    private static double getInformationFromUser(String message, Scanner scanner) {
        System.out.print("Enter the " + message);
        return scanner.nextDouble();
    }
}