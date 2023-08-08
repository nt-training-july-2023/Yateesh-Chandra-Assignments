package OperatorsAndExpressions;

import java.util.Scanner;

public class RootsOfQuadraticEquations {

	public static void main(String args[]) {
		
		// Square Root of the Quadratic equation ax^2 + bx + c = 0 is -b+/-(sqrt(b^2 - 4ac)/2a 
		
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the value of a in the quadratic equation: ");
		double a = s.nextInt();
		
		System.out.println("Enter the value of b in the quadratic equation: ");
		double b = s.nextInt();
		
		System.out.println("Enter the value of c in the quadratic equation: ");
		double c = s.nextInt();
		
		double root1, root2;
		double d = (b*b)-(4*a*c);
		
		
		if(d>0) {
		
		double sq = Math.sqrt(d);
		root1 = (-b + sq)/ (2*a);
		root2 = (-b - sq)/ (2*a);
			
		System.out.println("The roots of the quadratic equation are:\n root 1 : " + root1 + "\n root 2 : " + root2);
	}
		else if (d == 0) {
            double root = -b / (2 * a);
            System.out.println("Root: " + root);
        } else {
            System.out.println("No real roots. The discriminant is negative.");
        }
	s.close();
	}
	
	
}
