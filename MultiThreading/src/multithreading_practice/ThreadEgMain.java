package multithreading_practice;

public class ThreadEgMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ThreadEG t1 = new ThreadEG();
		ThreadEG t2 = new ThreadEG();
		t1.setName("t1");
		t2.setName("t2");
		t1.start();
		t2.start();
	}

}
