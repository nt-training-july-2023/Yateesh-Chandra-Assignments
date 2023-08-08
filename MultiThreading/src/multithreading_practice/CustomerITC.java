package multithreading_practice;

/*import java.util.Scanner;*/

public class CustomerITC {

	/*
	 * Scanner s = new Scanner(System.in);
	 * 
	 * System.out.println("Enter the amount : ");
	 */
	int amount = 10000;
	
	synchronized void withdraw(int amount) {
		System.out.println("Going to withdraw");
	
	if(this.amount<amount) {
		System.out.println("Less Balance; Waiting for deposit...");
		
		try{
			wait();
			}
		catch(Exception e)
		{
			
		}
	}
	this.amount -= amount;
	
	System.out.println("Withdraw completed");
	}
	
	synchronized void deposit(int amount) {
		System.out.println("going to deposit..");
		this.amount += amount;
		System.out.println("deposit completed..!");
		notify();
	}
}