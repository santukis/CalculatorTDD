package com.frikiplanet.calculatortdd;


import com.google.common.truth.Truth;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static com.google.common.truth.Truth.assertThat;
import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    @Parameters(method = "getValidAdditionInput")
    @Test
    public void additionShouldReturnExpectedValueWhenOperandsAreValid(
            double operand1, double operand2, double expectedValue) {
        //ACT OR WHEN
        double result = calculator.addition(operand1, operand2);

        //ASSERT OR THEN
        assertEquals(String.format("Result %1s should be equal to expectedValue %2s", result, expectedValue),
                expectedValue, result, 0.0);
    }

    private Object[] getValidAdditionInput() {
        return new Object[]{
                new Object[]{1, 1, 2},
                new Object[]{2, -2, 0},
                new Object[]{3.5, 2.7, 6.2},
                new Object[]{6.0, 4.0, 10.0},
                new Object[]{4.0, 4.0, 8.0}};
    }

    @Parameters(method = "getInvalidOperands")
    @Test(expected = ArithmeticException.class)
    public void additionShouldThrowsWhenOperandsOrResultAreInvalid(double operand1, double operand2) {
        calculator.addition(operand1, operand2);
    }

    private Object[] getInvalidOperands() {
        return new Object[]{
                new Object[]{12, Double.MAX_VALUE},
                new Object[]{Double.POSITIVE_INFINITY, 1},
                new Object[]{-12.3, Double.NEGATIVE_INFINITY},
                new Object[]{Double.NaN, 12}
        };
    }

    @Parameters(method = "getValidSubtractionInput")
    @Test
    public void subtractionShouldReturnExpectedValueWhenOperandsAreValid(
            double operand1, double operand2, double expectedValue) {
        double result = calculator.subtraction(operand1, operand2);

        assertEquals(String.format("Result %1s should be equal to expectedValue %2s", result, expectedValue),
                result, expectedValue, 0.00001);
    }

    private Object[] getValidSubtractionInput() {
        return $(
                $(1, 1, 0),
                $(-1, -1, 0),
                $(-1, 1, -2),
                $(3.2, 2.5, 0.7)
        );
    }

    @Parameters(method = "getInvalidOperands")
    @Test(expected = ArithmeticException.class)
    public void subtractionShouldThrowsWhenOperandsAreInvalid(double operand1, double operand2) {
        calculator.subtraction(operand1, operand2);
    }

    @Parameters(method = "getValidMultiplicationInput")
    @Test
    public void multiplicationShouldReturnExpectedValueWhenOperandsAndResultAreValid(
            double operand1, double operand2, double expectedValue) {
        double result = calculator.multiplication(operand1, operand2);

        //Truth
        assertThat(result).isWithin(1.0e-10).of(expectedValue);
    }

    private Object[] getValidMultiplicationInput() {
        return $(
                $(1, 1, 1),
                $(12, 0, 0),
                $(-0.3, 3, -0.9),
                $(-2, -3.2, 6.4)
        );
    }

    @Parameters(method = "getInvalidMultiplicationInput")
    @Test(expected = ArithmeticException.class)
    public void multiplicationShouldThrowsWhenOperandsOrResultAreInvalid(
            double operand1, double operand2) {
        calculator.multiplication(operand1, operand2);
    }

    private Object[] getInvalidMultiplicationInput() {
        return $(
                $(Double.MAX_VALUE, 0),
                $(Double.NaN, -1),
                $(-12.4, Double.POSITIVE_INFINITY),
                $(Double.NEGATIVE_INFINITY, -0.8),
                $(Math.pow(2, 1000), Math.pow(2, 1000)),
                $(Math.pow(2, 1023), 2),
                $(Math.pow(2, 1024), 0)
        );
    }
}