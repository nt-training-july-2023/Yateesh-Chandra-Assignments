package multithreading_practice;

public class ThreadStateExample {

	static class Mythread extends Thread{
		
		public void run() {
			System.out.println("Thread " + Thread.currentThread().getName()+"is in RUNNABLE STATE");
			
			try {
				Thread.sleep(1000);
			}
			catch(InterruptedException e) {
				e.getStackTrace();
			}
			
			System.out.println("Thread " + Thread.currentThread().getName()+"is in TERMINATED STATE");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Main Method is in RUNNABLE STATE");
		
		Thread t = new Thread();
		
		System.out.println("Thread " + t.getName()+" is in NEW STATE");
		
		t.start();
		
		try {
			t.join();
		}
		catch(InterruptedException e) {
			
			e.printStackTrace();
		}
		
		System.out.println("Main Thread is in TERMINATED STATE");
	}

}
