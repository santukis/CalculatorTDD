package com.frikiplanet.calculatortdd;


class Calculator {

    public double addition(double operand1, double operand2) throws ArithmeticException {

        if(operand1 == Double.MAX_VALUE || operand2 == Double.MAX_VALUE || Double.isInfinite(operand1)
              || Double.isInfinite(operand1) || Double.isNaN(operand1) || Double.isNaN(operand2))
            throw new ArithmeticException();

        return operand1 + operand2;
    }
}
