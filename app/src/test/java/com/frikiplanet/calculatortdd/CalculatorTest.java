package com.frikiplanet.calculatortdd;


import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(JUnitParamsRunner.class)
public class CalculatorTest {

    @Parameters(method = "getValidAdditionInput")
    @Test
    public void additionShouldReturnExpectedValueWhenOperandsAreValid(
            double operand1, double operand2, double expectedValue) {
        //ARRANGE OR GIVEN
        Calculator calculator = new Calculator();

        //ACT OR WHEN
        double result = calculator.addition(operand1, operand2);

        //ASSERT OR THEN
        assertEquals(String.format("Result %1s should be equal to expectedValue %2s", result, expectedValue),
                expectedValue, result, 0.0);
    }

    private Object[] getValidAdditionInput() {
        return new Object[] {
                new Object[] {1, 1, 2},
                new Object[] {2, -2, 0},
                new Object[] {3.5, 2.7, 6.2},
                new Object[] {6.0, 4.0, 10.0},
                new Object[] {4.0, 4.0, 8.0}};
    }

    @Parameters(method = "getInvalidAdditionInput")
    @Test(expected = ArithmeticException.class)
    public void additionShouldThrowsWhenOperandsOrResultAreInvalid(double operand1, double operand2) {
        Calculator calculator = new Calculator();

        calculator.addition(operand1, operand2);
    }

    private Object[] getInvalidAdditionInput() {
        return new Object[] {
                new Object[] {12, Double.MAX_VALUE},
                new Object[] {Double.POSITIVE_INFINITY, 1},
                new Object[] {-12.3, Double.NEGATIVE_INFINITY},
                new Object[] {Double.NaN, 12}
        };
    }
}