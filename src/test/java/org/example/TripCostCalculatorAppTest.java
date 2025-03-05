package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TripCostCalculatorAppTest {
    @Test
    void testGetInformationFromUser() throws Exception {
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream("150\n".getBytes()));
            Scanner scanner = new Scanner(System.in);

            Method method = TripCostCalculatorApp.class.getDeclaredMethod("getInformationFromUser", String.class, Scanner.class);
            method.setAccessible(true);

            double result = (double) method.invoke(null, "distance to travel (in km): ", scanner);
            assertEquals(150, result, 0.01);
        } finally {
            System.setIn(stdin);
        }
    }

    @Test
    void testCalculateFinalTripCost() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        Method method = TripCostCalculatorApp.class.getDeclaredMethod("calculateFinalTripCost", double.class, double.class);
        method.setAccessible(true);
        method.invoke(null, 100, 2.0);

        System.setOut(originalOut);

        String expectedOutput = "Total trip cost: 10,00";
        assertTrue(outContent.toString().contains(expectedOutput), "Expected output not found in console output");
    }

    @Test
    void testMainMethodExecution() {
        InputStream stdin = System.in;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setIn(new ByteArrayInputStream("100\n2.0\n".getBytes()));

            System.setOut(new PrintStream(outContent));

            TripCostCalculatorApp.main(new String[]{});

            System.setOut(originalOut);
            System.setIn(stdin);

            String expectedOutput = "Total trip cost: 10,00";
            assertTrue(outContent.toString().contains(expectedOutput), "Expected main method output not found");
        } finally {
            System.setOut(originalOut);
            System.setIn(stdin);
        }
    }
}