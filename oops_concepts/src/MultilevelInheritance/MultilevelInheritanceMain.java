package MultilevelInheritance;

import java.util.Scanner;

public class MultilevelInheritanceMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AllCalculations ac = new AllCalculations();
		
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the value of a : ");
		int a = s.nextInt();
		System.out.println("Enter the value of b : ");
		int b = s.nextInt();
		
		ac.addition(a, b);
		ac.subtraction(a, b);
		ac.multiplication(a, b);
		ac.division(a,b);
		
		s.close();
	}

}
