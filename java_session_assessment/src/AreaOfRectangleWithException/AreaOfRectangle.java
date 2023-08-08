package AreaOfRectangleWithException;

import java.util.Scanner;

public class AreaOfRectangle {

	public static void main(String args[])
	{
		try {
		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter the Length of the Rectangle");
		double length = getValidInput(s);
		
		System.out.println("Enter the Breadth of the Rectangle");
		double breadth = getValidInput(s);
		
		if(length<=0 || breadth == 0) {
			throw new InvalidDimensionException("No such Rectangle exists with the existing dimensions");
		}
		System.out.println("The area of the rectangle is : " + (length*breadth));
		}
		
		catch(InvalidDimensionException e){
			System.out.println(e);
		}
	}

	private static double getValidInput(Scanner s) throws InvalidDimensionException{
		  String in = s.nextLine(); try { double value = Double.parseDouble(in);
		  if(value < 0) { throw new
		  InvalidDimensionException("Invalid Input.. Please enter a positive value : "); }
		  return value; } catch(NumberFormatException ex) { throw new
		  InvalidDimensionException("Invalid input.. Please enter a numerical value : "); }
		  }

}
