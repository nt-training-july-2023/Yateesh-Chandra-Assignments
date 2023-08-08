package multithreading_practice;

public class MutexExample {

	private static Object printerMutex = new Object();
	
	@SuppressWarnings("deprecation")
	public static void main(String args[]) {
		Runnable printTask = () ->{
			for(int i =1; i <= 5 ; i++ ) {
				printDocument("Thread : "+ Thread.currentThread().getId(), "Document " + i);
			}
		};
		
		Thread t1 = new Thread(printTask);
		Thread t2 = new Thread(printTask);
		
		t1.start();
		t2.start();
	}

	private static void printDocument(String threadName, String document) {
		// TODO Auto-generated method stub
		synchronized (printerMutex) {
			System.out.println(threadName + " is printing : " + document );
			try {
				Thread.sleep(10);
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(threadName + " finished printing : " + document);
		} 
	}
}
