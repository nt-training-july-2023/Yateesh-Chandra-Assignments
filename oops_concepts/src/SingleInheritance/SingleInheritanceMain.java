package SingleInheritance;

import java.util.Scanner;

public class SingleInheritanceMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MyCalculation mc = new MyCalculation();
		
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the value of a : ");
		int a = s.nextInt();
		System.out.println("Enter the value of b : ");
		int b = s.nextInt();
		
		mc.addition(a, b);
		mc.multiplication(a, b);
		
		s.close();
	}

}
