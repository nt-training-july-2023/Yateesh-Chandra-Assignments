package Strings;

import java.util.Scanner;

public class ReplaceString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the String : ");
		String s = sc.nextLine();
		
		//6. Replace with the character or String
		
		System.out.println("Enter which character to be replaced : ");
		String ch = sc.nextLine();
		
		System.out.println("Enter the character in the string to replace: ");
		String re = sc.nextLine();
		
		String str = s.replace(ch, re);
		
		System.out.println("The new String is : "+str);
		
		sc.close();
		
	}

}
