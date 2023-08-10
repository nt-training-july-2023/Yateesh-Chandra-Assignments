package Files;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class EvenOrOdd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
				
			Scanner s= new Scanner(System.in);
			
			System.out.println("Enter a number to check whether the number is even or odd : ");
			int num = s.nextInt();
			
			FileWriter file = new FileWriter("even.txt");
			
			PrintWriter printWriter = new PrintWriter(file);
			
			printWriter.print("The Entered Number " + num + " is : ");
			
			if(num%2 == 0) {
				printWriter.print("even");
			}
			
			else {
				printWriter.print("odd");
			}
			
			printWriter.close();
			file.close();
			s.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			System.out.println("Output printed successfully..! Check even.txt for the output");
		}

	}

}
