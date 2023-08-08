package multithreading_practice;

public class SingleThreadedClass extends Thread{
	
	public void run() {
		int i = 1;
		while(i < 5) {
		System.out.println("Running in state : " + i);
		i++;
		}
	}

	
}
