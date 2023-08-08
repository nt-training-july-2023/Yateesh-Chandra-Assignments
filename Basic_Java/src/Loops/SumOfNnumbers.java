package Loops;

import java.util.Scanner;

public class SumOfNnumbers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int num, i =1, sum=0;
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the number to print the sum upto that number: ");
		num = s.nextInt();
		
		while(i<=num)
		{
			sum = num+i;
			i++;
		}
		
		System.out.println("The sum of the numbers is: "+ sum);
		
		s.close();
	}

}
