package multithreading_practice;

public class ThreadPriorityExample {

	static class MyRunnable implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i = 0; i< 5; i++)
			{
				System.out.println("Executing Thread Number : "+i);
				try {
					Thread.sleep(1000);
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Thread newThread = new Thread(new MyRunnable()) ;
		newThread.setPriority(Thread.MAX_PRIORITY);
		newThread.start();
		
		for(int i = 0; i< 5; i++)
		{
			System.out.println("Executing from main Thread Number : "+i);
			try {
				Thread.sleep(1000);
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
				
	}

}
