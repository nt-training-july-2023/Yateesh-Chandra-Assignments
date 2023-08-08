package multithreading_practice;

public class MultiThreadingRunnable implements Runnable{

	public void run() {
		int i = 1;
		while(i < 5) {
			System.out.println("This is runnable in "+i);
			i++;
		}
	}
}
