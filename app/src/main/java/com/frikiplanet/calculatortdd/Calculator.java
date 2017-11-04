package com.frikiplanet.calculatortdd;


class Calculator {

    public double addition(double operand1, double operand2) throws ArithmeticException {

        throwsIfNumbersAreInvalid(operand1, operand2);

        return operand1 + operand2;
    }

    public double subtraction(double operand1, double operand2) {

        throwsIfNumbersAreInvalid(operand1, operand2);

        return operand1 - operand2;
    }

    public double multiplication(double operand1, double operand2) {

        throwsIfNumbersAreInvalid(operand1, operand2);

        double result = operand1 * operand2;

        System.out.println("multiplication result: " + (operand1 * operand2));

        throwsIfNumbersAreInvalid(result);

        return result;
    }

    public double division(double dividend, double divisor) {

        throwsIfNumbersAreInvalid(dividend, divisor);

        double result = dividend / divisor;

        throwsIfNumbersAreInvalid(result);

        return result;
    }

    private void throwsIfNumbersAreInvalid(double... numbers) throws ArithmeticException {

        for (Double number : numbers) {
            if (number == Double.MAX_VALUE || Double.isInfinite(number) || Double.isNaN(number))
                throw new ArithmeticException();
        }
    }

    public double exponentiation(double base, double exponent) {
        throwsIfNumbersAreInvalid(base, exponent);

        double result = Math.pow(base, exponent);

        throwsIfNumbersAreInvalid(result);

        return result;
    }

    public double squareRoot(double radicand) {

        throwsIfNumbersAreInvalid(radicand);

        double result = Math.sqrt(radicand);

        throwsIfNumbersAreInvalid(result);

        return result;
    }

    public double logarithm10(double operand) {

        throwsIfNumbersAreInvalid(operand);

        double result = Math.log10(operand);

        throwsIfNumbersAreInvalid(result);

        return result;
    }

    public double naturalLogarithm(double operand) {

        throwsIfNumbersAreInvalid(operand);

        double result = Math.log(operand);

        throwsIfNumbersAreInvalid(result);

        return result;
    }

    public double factorial(double operand) throws ArithmeticException, StackOverflowError {

        throwsIfNumbersAreInvalid(operand);

        if (operand == 0 || operand == 1)
            return 1;

        return operand * factorial(operand - 1);
    }
}
