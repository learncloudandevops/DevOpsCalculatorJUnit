package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    void addsTwoNumbers() {
        assertEquals(15, calculator.add(10, 5));
    }

    @Test
    void subtractsTwoNumbers() {
        assertEquals(5, calculator.subtract(10, 5));
    }

    @Test
    void multipliesTwoNumbers() {
        assertEquals(50, calculator.multiply(10, 5));
    }

    @Test
    void dividesTwoNumbers() {
        assertEquals(2, calculator.divide(10, 5));
    }

    @Test
    void divisionByZeroThrowsException() {
        assertThrows(
            ArithmeticException.class,
            () -> calculator.divide(10, 0)
        );
    }
}
