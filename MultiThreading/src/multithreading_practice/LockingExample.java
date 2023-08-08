package multithreading_practice;

public class LockingExample {

	private static int counter = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		 * Runnable incrementTask = () ->{ for(int i = 0; i < 10; i ++) {
		 * incrementCounter(); } };
		 */
		
		Runnable incrementTask = new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i = 0; i < 10; i++) {
					incrementCounter();
				}
			}
			
		};
		
		Thread thread1 = new Thread(incrementTask);
		Thread thread2 = new Thread(incrementTask);
		/* Thread thread3 = new Thread(incrementTask); */
		
		thread1.start();
		thread2.start();
		
		
		try {
			thread1.join();
			thread2.join();
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		
		System.out.println("Final Counter value : "+counter);
	}
	private synchronized static void incrementCounter() {
		// TODO Auto-generated method stub
		counter++;
	}

}
