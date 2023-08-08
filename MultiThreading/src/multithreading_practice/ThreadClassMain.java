
package multithreading_practice;

public class ThreadClassMain {

	public static void main(String args[]) {
		ThreadClass thread = new ThreadClass();
		thread.start();
		thread.pause();
		int i = 1;
		while(i<5) {
			System.out.println("Main Method display" + i);
			i++;
		}
	}
}
