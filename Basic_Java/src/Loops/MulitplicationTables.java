package Loops;

import java.util.Scanner;

public class MulitplicationTables {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter the number to print its Multiplication Table:");
		int num = s.nextInt();
		
		System.out.println("Enter the number of Multiples:");
		int mul = s.nextInt();
		
		for (int i = 1; i<=mul; i++) {
			
			System.out.println(num + " * " + i + " = " + (num * i));
		}
		
		s.close();
	}

}
