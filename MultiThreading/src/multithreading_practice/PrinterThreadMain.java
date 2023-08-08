package multithreading_practice;

public class PrinterThreadMain {
	public static void main(String[] args) {
        Object lockA = new Object();
        Object lockB = new Object();

        PrinterThread threadA = new PrinterThread("HELLO", lockA, lockB);
        PrinterThread threadB = new PrinterThread("WORLD", lockB, lockA);

        threadA.start();
        threadB.start();
    }	
}
