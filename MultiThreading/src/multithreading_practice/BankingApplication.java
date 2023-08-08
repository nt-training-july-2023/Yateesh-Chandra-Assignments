package multithreading_practice;

public class BankingApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BankAccount b = new BankAccount(1000);
		
		Thread depositThread = new Thread(()->{
			for(int i=0; i<=5; i++) {
				b.deposit(100);
				try {
					Thread.sleep(500);
				}
				catch(InterruptedException e) {
					e.getStackTrace();
				}
			}
		});
		
		Thread withdrawThread = new Thread(()->{
			for(int i = 0; i<=5; i++) {
				b.withdraw(200);
				try {
					Thread.sleep(700);
				}
				catch(InterruptedException e) {
					e.getStackTrace();
				}
			}
		});
		
		depositThread.start();
		withdrawThread.start();
	}

}
