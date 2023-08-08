package multithreading_practice;

public class SingleThreadedClassMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SingleThreadedClass stc = new SingleThreadedClass();
		stc.start();
		
		int i = 1;
		while(i < 5) {
			System.out.println("The main method is running at state : " + i);
			i++;
		}
	}

}
