package Strings;

import java.util.Scanner;

public class StartsWith {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//4. Check whether a string starts with a character or not
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the String : ");
		String s = sc.nextLine();
		
		System.out.println("Enter a character to check the string starts with that : ");
		String start = sc.nextLine();
		boolean st = s.startsWith(start);
		if(st == true) {
			System.out.println("Yes..! The String starts with " + start);
		}
		else {
			System.out.println("Oops..! The String does not start with " + start);
		}
		
		sc.close();
	}

}
