package Strings;

import java.util.Scanner;

public class FindIndex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the String : ");
		String s = sc.nextLine();
		
		//5. Find the index of a character or a string from the String
		
		System.out.println("Enter the character to check its index : ");
		String ch = sc.nextLine(); 
		int loc = s.indexOf(ch);
		
		if(loc == -1) {
		System.out.println("The string does not contain the entered Character");
		}
		
		else {
			System.out.println(loc);
		}
		
		sc.close();
	}

}
