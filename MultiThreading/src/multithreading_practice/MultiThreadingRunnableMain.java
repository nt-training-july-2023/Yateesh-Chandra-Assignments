package multithreading_practice;

public class MultiThreadingRunnableMain {

	public static void main(String args[]) {
		
		MultiThreadingRunnable m = new MultiThreadingRunnable();
		Thread t = new Thread(m);
		t.start();
		
		int i = 1;
		while(i < 5) {
		System.out.println("Main method running in : " + i);
		i++;
		}
	}
}
