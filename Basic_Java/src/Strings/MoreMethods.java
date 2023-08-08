package Strings;

import java.util.Scanner;

public class MoreMethods {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String str;
		Scanner s = new Scanner(System.in);
		System.out.println("Enter a String : ");
		str = s.nextLine();
		
		//1. Converting to Lower case		
		System.out.println("The String in Lower Case : "+str.toLowerCase());
		
		//2. Converting to Upper case
		System.out.println("The String in Upper Case : "+str.toUpperCase());
		
		//3. SubString from a string
		System.out.println("Enter the index to generate the substring from there : ");
		int begin = s.nextInt();
		System.out.println("Enter the index to generate the substring from there : ");
		int end = s.nextInt();
		System.out.println("The substring from there is : "+str.substring(begin, end));
		
		//4. Trimming
		String trimmed = str.trim();
		System.out.println("The string with leading and trailing spaces removed : "+trimmed);
		
		System.out.println("The Length of the normal string is : " +str.length() + "\nThe Length of the trimmed string is : "+trimmed.length());
		s.close();
		
	}

}
