package Strings;

public class EqualsAndOperator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		//equals method
		String str1 = "Hello";
		String str2 = "hello";
		boolean result = str1.equals(str2); // false (case-sensitive comparison)

		System.out.println(result);
		
		// == Operator
		String str3 = "Hello";
		String str4 = "hello";
		boolean result1 = str3 == str4; // true (both point to the same memory location)

		System.out.println(result1);
	}

}
