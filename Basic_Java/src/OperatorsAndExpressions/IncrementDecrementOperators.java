package OperatorsAndExpressions;

public class IncrementDecrementOperators {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int num1 = 5;
        int num2 = 10;

        // Post-increment operator
        int a = num1++;
        System.out.println("Post-increment: num1 = " + num1 + ", result1 = " + a);

        // Pre-increment operator
        int b = ++num2;
        System.out.println("Pre-increment: num2 = " + num2 + ", result2 = " + b);
        
        //Post-decrement operator
        int c = num1--;
        System.out.println("Post-decrement: num1 = " + num1 + ", result1 = " + c);

        // Pre-decrement operator
        int d = --num2;
        System.out.println("Pre-decrement: num2 = " + num2 + ", result2 = " + d);
    }
	
}

