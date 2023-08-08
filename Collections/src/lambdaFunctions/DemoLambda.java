package lambdaFunctions;

import java.util.Scanner;

interface A{
	void show(String message);
}

public class DemoLambda {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		A obj = (message) ->	System.out.println("This is Show Method..! It has the value and it is showing : " + message);
			
		Scanner s = new Scanner(System.in);
		System.out.println("Enter a message : ");
		String input = s.nextLine();
		obj.show(input);
		s.close();

}
}
