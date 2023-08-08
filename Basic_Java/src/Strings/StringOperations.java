package Strings;

import java.util.Scanner;

public class StringOperations {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the String : ");
		String s = sc.nextLine();
		
		//1. The Length of the String
		System.out.println("The length of the String is : " + s.length());
		
		
		//2. String Concatenation
		System.out.println("Enter the second string:");
		String s2 = sc.nextLine();
		s = s.concat(s2);
		System.out.println("The New String is : " + s);
		
		//3. Character at a given index
		System.out.println("Enter the index of the Element to return that character : ");
		int index = sc.nextInt();
		System.out.println("The String at position is : "+s.charAt(index));		
		
		sc.close();
	}

}
