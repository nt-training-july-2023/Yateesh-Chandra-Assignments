package multithreading_practice;

public class ThreadClass extends Thread{

	
	  public void run() { // TODO Auto-generated method stub 
	  int i = 1; while(i<5)
	  { System.out.println("Run Method display..!" + i); i++; }
	  
	  }
	 
	
	public void pause() {
		int j = 1;
		while(j < 5) {
			System.out.println("Pause for " + j);
			j++;
		}
	}

}
