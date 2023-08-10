package Files;

import java.io.Console;

public class ReadPassword {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Console c = System.console();
		System.out.println("Enter your password : ");
		
		char[] ch = c.readPassword();
		String pass = String.valueOf(ch);
		
		System.out.println("Password is : " + pass);
	}

}
