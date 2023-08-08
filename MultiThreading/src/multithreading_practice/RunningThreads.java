package multithreading_practice;

public class RunningThreads {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int i = 0;
		Thread thread1 = new Thread(() -> {
            while (i<5) {
                // Do some work or processing
            }
        });

        Thread thread2 = new Thread(() -> {
            while (i<5) {
                System.out.println("Thread2");
            }
        });

        Thread thread3 = new Thread(() -> {
            while (i< 5) {
               System.out.println("Thread3");
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

        // Give some time for threads to start running
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check running threads
        ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();
        int activeThreads = currentGroup.activeCount();
        Thread[] threads = new Thread[activeThreads];
        currentGroup.enumerate(threads);

        System.out.println("Number of running threads: " + activeThreads);
        System.out.println("=========================================");

        for (Thread thread : threads) {
            System.out.println("Thread Name: " + thread.getName());
        }
	}

}
