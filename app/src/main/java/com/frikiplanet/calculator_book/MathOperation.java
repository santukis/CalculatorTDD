package com.frikiplanet.calculator_book;


public class MathOperation {

   public double addition(double operand1, double operand2) throws OperationException {

      throwsIfValuesAreInvalid(operand1, operand2);

      return operand1 + operand2;
   }


   public double subtraction(double operand1, double operand2) throws OperationException {

      throwsIfValuesAreInvalid(operand1, operand2);

      return operand1 - operand2;
   }

   public double multiplication(double operand1, double operand2) throws OperationException {

      double result = operand1 * operand2;

      throwsIfValuesAreInvalid(result);

      return result;
   }

   public double division(double dividend, double divisor) throws OperationException {

      throwsIfValuesAreInvalid(dividend, divisor);

      double result = dividend / divisor;

      throwsIfValuesAreInvalid(result);

      return result;
   }

   public double exponentiation(double base, double exponent) throws OperationException {

      if ((base == 0 || base == 1) && exponent != 0)
         return base;

      throwsIfValuesAreInvalid(base, exponent);

      double result = 1;
      boolean expoNegative = exponent < 0;

      if (expoNegative)
         exponent = -exponent;

      while (exponent != 0) {
         result = multiplication(result, base);
         exponent--;
      }

      return expoNegative ? (1 / result) : result;
   }

   public double squareRoot(double radicand) throws OperationException {

      double aux;
      double squareRoot = radicand / 2;

      if (radicand < 0) {
         throw new OperationException();
      }

      do {
         aux = squareRoot;
         squareRoot = (aux + (radicand / aux)) / 2;

         throwsIfValuesAreInvalid(squareRoot);
      }
      while (aux != squareRoot);

      return squareRoot;

   }

   public double factorial(double operand) throws OperationException {

      if (operand < 0)
         throw new OperationException("result is not valid");

      double result = 1;

      while (operand != 0) {
         result = multiplication(result, operand);
         operand--;
      }

      return result;
   }

   private void throwsIfValuesAreInvalid(double... values) throws OperationException {

      for (Double value : values) {
         if (value == Double.MAX_VALUE || Double.isInfinite(value) || Double.isNaN(value))
            throw new OperationException();
      }
   }
}