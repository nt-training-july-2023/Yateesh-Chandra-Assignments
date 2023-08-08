package multithreading_practice;

public class PrinterThread extends Thread{
	private String word;
    private Object currentLock;
    private Object nextLock;

    public PrinterThread(String word, Object currentLock, Object nextLock) {
        this.word = word;
        this.currentLock = currentLock;
        this.nextLock = nextLock;
    }

    @Override
    public void run() {
        synchronized (currentLock) {
            for (int i = 0; i < word.length(); i++) {
                System.out.print(word.charAt(i));

                try {
                    synchronized (nextLock) {
                        nextLock.notify();
                    }
                    if (i < word.length() - 1) {
                        currentLock.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }}
