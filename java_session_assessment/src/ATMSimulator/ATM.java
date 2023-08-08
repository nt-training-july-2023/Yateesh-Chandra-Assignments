package ATMSimulator;

import java.util.Scanner;

public class ATM {

	public static void main(String args[]) {
		
		try {
		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter the Account Balance : ");
		double acc = getValidInput(s);
		
		System.out.println("Enter the Amount to withdraw : ");
		double amt = getValidInput(s);
		
		if(acc<amt) {
			throw new InvalidInputException("Insufficient Balance");
		}
		System.out.println("Withdrawl Successful..! Remaining Balance : " + (acc-amt));
		}
		
		catch(InvalidInputException e){
			System.out.println(e);
		}
		
	}
	
	
	  private static double getValidInput(Scanner s) throws InvalidInputException{
	  String in = s.nextLine(); try { double value = Double.parseDouble(in);
	  if(value < 0) { throw new
	  InvalidInputException("Invalid Input.. Please enter a positive value : "); }
	  return value; } catch(NumberFormatException ex) { throw new
	  InvalidInputException("Invalid input.. Please enter a numerical value : "); }
	  }
	 
}
