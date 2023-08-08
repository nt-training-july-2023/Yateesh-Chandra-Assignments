package multithreading_practice;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {

	private double balance;
	private final Lock lock = new ReentrantLock();
	public BankAccount(double initialBalance) {
		super();
		this.balance = initialBalance;
	}
	
	public void deposit(double amount) {
		lock.lock();
		try {
			balance+=amount;
			System.out.println(Thread.currentThread().getName()+" deposited : "+amount);
			System.out.println("The new balance is : " + balance);
		}
		finally {
			lock.unlock();
		}
	}
	
	public void withdraw(double amount) {
		lock.lock();
		try {
			if(balance >= amount) {
			balance-=amount;
			System.out.println(Thread.currentThread().getName()+" withdrawn : "+amount);
			System.out.println("The new balance is : " + balance);
		}
			else {
				System.out.println(Thread.currentThread()+" Tried to withdraw Rs."+amount+"There is no sufficient amount");
			}
		}
		finally {
			lock.unlock();
		}
	}
	
}
