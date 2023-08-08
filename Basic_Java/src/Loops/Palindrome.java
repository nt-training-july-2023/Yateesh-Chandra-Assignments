package Loops;

import java.util.Scanner;

public class Palindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int num, rev = 0;
		Scanner s = new Scanner(System.in);
		System.out.println("Enter a number to check if it is a palindrome : ");
		num = s.nextInt();
		
		int originalNum = num;
				
		while(num !=0) {
		int digit = num % 10;
	      rev = rev * 10 + digit;
	      num /= 10;
	}
		
		System.out.println("Reverse : " + rev);
		if(originalNum == rev) {
			System.out.println("The Number is a Palindrome ");	
		}
		
		else {
			System.out.println("The Number is not  a Palindrome ");
		}
		
		s.close();

	}

}
