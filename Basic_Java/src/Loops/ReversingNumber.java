package Loops;

import java.util.Scanner;

public class ReversingNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int num, rev = 0;
		Scanner s = new Scanner(System.in);
		System.out.println("Enter a number to reverse its digits : ");
		num = s.nextInt();
		
		while(num !=0) {
		int digit = num % 10;
	      rev = rev * 10 + digit;
	      num /= 10;
	}
		
		System.out.println("The Reverse of the number : " + rev);
		s.close();
	}
}
