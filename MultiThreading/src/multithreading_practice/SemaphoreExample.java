package multithreading_practice;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {

	private static Semaphore printerSemaphore = new Semaphore(1);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("deprecation")
		Runnable printTask = () ->{
			try {
				printerSemaphore.acquire();
				printDocument("Thread " + Thread.currentThread().getId());
				printerSemaphore.release();
				
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		};
		
		Thread thread1 = new Thread(printTask);
		Thread thread2 = new Thread(printTask);
		Thread thread3 = new Thread(printTask);
		Thread thread4 = new Thread(printTask);
		
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();

	}

	private static void printDocument(String threadName) {
		// TODO Auto-generated method stub
		System.out.println(threadName + " is printing a document.!");
		
		try {
			Thread.sleep(10);
		}
		
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(threadName + " finished printing");
	}

}
