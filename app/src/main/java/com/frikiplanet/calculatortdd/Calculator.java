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

    private void throwsIfNumbersAreInvalid(double... numbers) throws ArithmeticException {

        for(Double number : numbers) {
            if(number == Double.MAX_VALUE || Double.isInfinite(number) || Double.isNaN(number))
                throw new ArithmeticException();
        }
    }
}
