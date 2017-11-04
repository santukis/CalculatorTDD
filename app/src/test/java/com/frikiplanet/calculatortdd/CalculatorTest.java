package com.frikiplanet.calculatortdd;


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

    @Parameters(method = "getValidDivisionInput")
    @Test
    public void divisionShouldReturnExpectedValueWhenOperandsAreValid(
            double operand1, double operand2, double expectedValue) {

        double result = calculator.division(operand1, operand2);

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
    @Test(expected = ArithmeticException.class)
    public void divisionShouldThrowWhenOperandsOrResultAreInvalid(
            double operand1, double operand2) {

        calculator.division(operand1, operand2);
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
    public void exponentiationShouldReturnExpectedValueWhenOperandsAreValid(
            double base, double exponent, double expectedValue) {

        double result = calculator.exponentiation(base, exponent);

        assertThat(result).isEqualTo(expectedValue);
    }

    private Object[] getValidExponentiationInput() {
        return $(
                $(2, 0, 1),
                $(2, 1, 2),
                $(2, 3, 8),
                $(2, -2, 0.25)
        );
    }

    @Parameters(method = "getInvalidExponentiationInput")
    @Test(expected = ArithmeticException.class)
    public void exponentiationShouldThrowWhenOperandsAreInvalid(
            double base, double exponent) {
        calculator.exponentiation(base, exponent);
    }

    private Object[] getInvalidExponentiationInput() {
        return $(
                $(0, -2),
                $(Double.NEGATIVE_INFINITY, 2),
                $(Double.NaN, -1),
                $(2, Double.MAX_VALUE)
        );
    }

    @Parameters(method = "getValidSquareRootInput")
    @Test
    public void squareRootShouldReturnExpectedValueWhenOperandsAreValid(
            double radicand, double expectedValue) {

        double result = calculator.squareRoot(radicand);

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
    @Test(expected = ArithmeticException.class)
    public void squareRootShouldThrowWhenOperandIsInvalid(double radicand) {
        calculator.squareRoot(radicand);
    }

    private Object[] getInvalidSquareRootInput() {
        return $(
                $(-3),
                $(Double.POSITIVE_INFINITY),
                $(Double.NaN),
                $(-2.5)
        );
    }

    @Parameters(method = "getValidLogarithm10Input")
    @Test
    public void logarithm10ShouldReturnExpectedValueWhenOperandIsValid(
            double operand, double expectedValue) {

        double result = calculator.logarithm10(operand);

        assertThat(result).isWithin(1.0e-5).of(expectedValue);
    }

    private Object[] getValidLogarithm10Input() {
        return $(
                $(4, 0.6020599913),
                $(49, 1.69019608),
                $(121, 2.0827853703)
        );
    }

    @Parameters(method = "getInvalidLogarithm10Input")
    @Test(expected = ArithmeticException.class)
    public void logarithm10ShouldThrowsWhenOperandAreInvalid(double operand) {
        calculator.logarithm10(operand);
    }

    private Object[] getInvalidLogarithm10Input() {
        return $(
                $(-3),
                $(Double.NEGATIVE_INFINITY),
                $(0),
                $(Double.NaN)
        );
    }

    @Parameters(method = "getValidNaturalLogInput")
    @Test
    public void naturalLogarithmShouldReturnExpectedValueWhenOperandIsValid(
            double operand, double expectedValue) {

        double result = calculator.naturalLogarithm(operand);

        assertThat(result).isWithin(1.0e-5).of(expectedValue);
    }

    private Object[] getValidNaturalLogInput() {
        return $(
                $(4, 1.386294),
                $(25, 3.218875)
        );
    }

    @Parameters(method = "getInvalidNaturalLogInput")
    @Test(expected = ArithmeticException.class)
    public void naturalLogarithmShouldThrowsWhenOperandIsInvalid(double operand) {
        calculator.naturalLogarithm(operand);
    }

    private Object[] getInvalidNaturalLogInput() {
        return $(
                $(-3),
                $(Double.POSITIVE_INFINITY),
                $(Double.NaN),
                $(0)
        );
    }

    @Parameters(method = "getValidFactorialInput")
    @Test
    public void factorialShouldReturnExpectedValueWhenOperandIsValid(
            double operand, double expectedValue) {

        double result = calculator.factorial(operand);

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

        try{
            calculator.factorial(operand);
            Assert.fail("factorial should throws an Exception/Error");

        } catch (ArithmeticException exception) {
            assertThat(exception).isNotNull();
            System.out.println("ArithmeticException has been thrown");

        } catch (StackOverflowError error) {
            assertThat(error).isNotNull();
            System.out.println("StackOverflowError has been thrown");
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