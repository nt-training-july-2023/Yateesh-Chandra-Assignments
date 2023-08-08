package multithreading_practice;

public class ThreadEG extends Thread{

	public void run() {
		int i = 0;
		while( i < 10) {
			System.out.println(Thread.currentThread().getName() + " i value : "+i++);
		}
	}
	
}
