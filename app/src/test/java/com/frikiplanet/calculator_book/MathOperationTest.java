package com.frikiplanet.calculator_book;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static com.google.common.truth.Truth.assertThat;
import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)

public class MathOperationTest {

    private MathOperation mathOperation;

    @Before
    public void setUp() {
        mathOperation = new MathOperation();
    }

    @Parameters(method = "getValidAdditionInput")
    @Test
    public void additionShouldReturnExpectedValueWhenOperandsAreValid(
            double operand1, double operand2, double expectedValue) {
        //ACT OR WHEN
        double result = mathOperation.addition(operand1, operand2);

        //ASSERT OR THEN
        assertEquals(String.format("Result %1s should be equal to expectedValue %2s", result, expectedValue),
                expectedValue, result, 0.0);
    }


    private Object[] getValidAdditionInput() {
        return new Object[]{
                new Object[]{1, 1, 2},
                new Object[]{2, -2, 0},
                new Object[]{Double.MIN_VALUE, 1, 1},
                new Object[]{3.5, 2.7, 6.2},
                new Object[]{6.0, 4.0, 10.0},
                new Object[]{4.0, 4.0, 8.0}};
    }

    @Parameters(method = "getInvalidInput")
    @Test(expected = OperationException.class)
    public void additionShouldThrowsWhenValuesAreInvalid(double operand1, double operand2) {
        mathOperation.addition(operand1, operand2);
    }

    private Object[] getInvalidInput() {
        return new Object[]{
                new Object[]{12, Double.MAX_VALUE},
                new Object[]{Double.POSITIVE_INFINITY, 1},
                new Object[]{-12.3, Double.NEGATIVE_INFINITY},
                new Object[]{Double.NaN, 12},
                new Object[]{Math.pow(2, 1024), -1}
        };
    }

    @Parameters(method = "getValidSubtractionInput")
    @Test
    public void subtractionShouldReturnExpectedValueWhenOperandsAreValid(
            double operand1, double operand2, double expectedValue) {
        double result = mathOperation.subtraction(operand1, operand2);

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

    @Parameters(method = "getInvalidInput")
    @Test(expected = OperationException.class)
    public void subtractionShouldThrowsWhenOperandsAreInvalid(double operand1, double operand2) {
        mathOperation.subtraction(operand1, operand2);
    }

    @Parameters(method = "getValidMultiplicationInput")
    @Test
    public void multiplicationShouldReturnExpectedValueWhenOperandsAndResultAreValid(
            double operand1, double operand2, double expectedValue) {
        double result = mathOperation.multiplication(operand1, operand2);

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
    @Test(expected = OperationException.class)
    public void multiplicationShouldThrowsWhenOperandsOrResultAreInvalid(
            double operand1, double operand2) {
        mathOperation.multiplication(operand1, operand2);
    }

    private Object[] getInvalidMultiplicationInput() {
        return $(
                $(Double.NaN, -1),
                $(-12.4, Double.POSITIVE_INFINITY),
                $(Double.NEGATIVE_INFINITY, -0.8),
                $(Math.pow(2, 1000), Math.pow(2, 1000)),
                $(Math.pow(2, 1023), 2),
                $(Math.pow(2, 1024), 0)
        );
    }

    @Parameters(method = "getValidDivisionInput")
    @Test
    public void divisionShouldReturnExpectedValueWhenOperandsAreValid(
            double operand1, double operand2, double expectedValue) {

        double result = mathOperation.division(operand1, operand2);

        assertThat(result).isWithin(1.0e-10).of(expectedValue);
    }

    private Object[] getValidDivisionInput() {
        return $(
                $(6, 3, 2),
                $(3, 2, 1.5),
                $(33, -5, -6.6),
                $(0.3, -0.25, -1.2)
        );
    }

    @Parameters(method = "getInvalidDivisionInput")
    @Test(expected = OperationException.class)
    public void divisionShouldThrowWhenOperandsOrResultAreInvalid(
            double operand1, double operand2) {

        mathOperation.division(operand1, operand2);
    }

    private Object[] getInvalidDivisionInput() {
        return $(
                $(Double.MAX_VALUE, 1),
                $(1.2, Double.NaN),
                $(Double.POSITIVE_INFINITY, 0.1),
                $(-12, Double.NEGATIVE_INFINITY),
                $(12, 0)
        );
    }

    @Parameters(method = "getValidExponentiationInput")
    @Test
    public void exponentiationShouldReturnExpectedValueWhenInputIsValid(
            double base, double exponent, double expectedValue) {

        double result = mathOperation.exponentiation(base, exponent);

        assertThat(result).isWithin(1.0e-5).of(expectedValue);
    }

    private Object[] getValidExponentiationInput() {
        return $(
                $(0, 0, 1),
                $(2, 0, 1),
                $(2, 1, 2),
                $(2.3, 5, 64.36343),
                $(-3, 4, 81),
                $(-3, 3, -27),
                $(2, -2, 0.25),
                $(-3, -5, -0.00411522633),
                $(0, 100_000_000, 0),
                $(1, 100_000_000, 1)
        );
    }

    @Parameters(method = "getInvalidExponentiationInput")
    @Test(expected = OperationException.class)
    public void exponentiationShouldThrowWhenOperandsAreInvalid(
            double base, double exponent) {
        mathOperation.exponentiation(base, exponent);
    }

    private Object[] getInvalidExponentiationInput() {
        return $(
                $(5, 3.3),
                $(-3, -1.2),
                $(Double.NEGATIVE_INFINITY, 2),
                $(-3, Double.POSITIVE_INFINITY),
                $(Double.NaN, -1),
                $(2, Double.MAX_VALUE),
                $(2, 1024)

        );
    }

    @Parameters(method = "getValidSquareRootInput")
    @Test
    public void squareRootShouldReturnExpectedValueWhenInputIsValid(
            double radicand, double expectedValue) {

        double result = mathOperation.squareRoot(radicand);

        assertThat(result).isWithin(1.0e-3).of(expectedValue);
    }

    private Object[] getValidSquareRootInput() {
        return $(
                $(4, 2),
                $(5, 2.236),
                $(15, 3.872),
                $(121, 11)
        );
    }

    @Parameters(method = "getInvalidSquareRootInput")
    @Test(expected = OperationException.class)
    public void squareRootShouldThrowWhenOperandIsInvalid(double radicand) {
        mathOperation.squareRoot(radicand);
    }

    private Object[] getInvalidSquareRootInput() {
        return $(
                $(-3),
                $(Double.POSITIVE_INFINITY),
                $(Double.NaN),
                $(-2.5)
        );
    }

    @Parameters(method = "getValidFactorialInput")
    @Test
    public void factorialShouldReturnExpectedValueWhenOperandIsValid(
            double operand, double expectedValue) {

        double result = mathOperation.factorial(operand);

        assertThat(result).isEqualTo(expectedValue);
    }

    private Object[] getValidFactorialInput() {
        return $(
                $(0, 1),
                $(1, 1),
                $(2, 2),
                $(3, 6),
                $(4, 24),
                $(5, 120),
                $(6, 720),
                $(10, 3_628_800)
        );
    }

    @Parameters(method = "getInvalidFactorialInput")
    @Test
    public void factorialShouldThrowsWhenOperandIsInvalid(double operand) {

        try {
            mathOperation.factorial(operand);
            Assert.fail("factorial should throws an Exception/Error");

        } catch (OperationException exception) {
            assertThat(exception).isNotNull();
            System.out.println("OperationException has been thrown");

        }
    }

    private Object[] getInvalidFactorialInput() {
        return $(
                $(Double.MAX_VALUE),
                $(Double.NaN),
                $(Double.POSITIVE_INFINITY),
                $(Double.NEGATIVE_INFINITY),
                $(1_000_000),
                $(-1),
                $(2.3),
                $(-2.3)
        );
    }
}