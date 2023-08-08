package multithreading_practice;

public class LoopedSingleFlow {

	public static void main(String args[]) {
		display("Display Message ..!");
		int i = 1;
		while(i<5) {
			System.out.println("Executing inside the main method..!" + i);
			i++;
		}
	}

	private static void display(String message) {
		// TODO Auto-generated method stub
		int i = 1;
		while(i < 10) {
			System.out.println(message + i);
			i++;
		}
	}
}
