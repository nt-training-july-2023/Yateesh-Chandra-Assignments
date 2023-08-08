package Loops;

import java.util.Scanner;

public class FactorialOfNumber {

	public static void main(String args[])
	
	{
		System.out.println("Enter the number to print its factorial : ");
		Scanner s = new Scanner(System.in);
		int i=1, fact = 1;
		int num = s.nextInt();
		
		while(i<=num)
		{
			 fact = fact*i;
			 i++;
		}
		
		System.out.println("The factorial of the number is : "+fact);
		
		s.close();
	}
}
