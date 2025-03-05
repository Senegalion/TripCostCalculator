package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TripCalculatorTest {

    public static final double DELTA = 0.01;

    TripCalculator tripCalculator = new TripCalculator();


    @ParameterizedTest
    @CsvSource({
            "100, 2.0, 10.0",
            "200, 1.5, 15.0",
            "50, 3.0, 7.5"
    })
    @DisplayName("Should correctly calculate trip cost for valid inputs")
    void testCalculateTripCostWithValidInputs(double distance, double fuelPrice, double expectedCost) {
        double cost = tripCalculator.calculateTripCost(distance, fuelPrice);
        assertEquals(expectedCost, cost, DELTA, "Trip cost calculation is incorrect");
    }

    @Test
    @DisplayName("Should throw exception when distance is zero or negative")
    void testCalculateTripCostWithInvalidDistance() {
        Exception exception = assertThrows(RuntimeException.class, () -> tripCalculator.calculateTripCost(0, 2.0));
        assertEquals("Entered invalid distance (should be greater than 0)!", exception.getMessage());

        exception = assertThrows(RuntimeException.class, () -> tripCalculator.calculateTripCost(-50, 2.0));
        assertEquals("Entered invalid distance (should be greater than 0)!", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when fuel price is zero or negative")
    void testCalculateTripCostWithInvalidFuelPrice() {
        Exception exception = assertThrows(RuntimeException.class, () -> tripCalculator.calculateTripCost(100, 0));
        assertEquals("Entered invalid fuel price (should be greater than 0)!", exception.getMessage());

        exception = assertThrows(RuntimeException.class, () -> tripCalculator.calculateTripCost(100, -2.0));
        assertEquals("Entered invalid fuel price (should be greater than 0)!", exception.getMessage());
    }
}