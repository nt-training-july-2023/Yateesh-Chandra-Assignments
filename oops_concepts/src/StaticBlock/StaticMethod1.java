package StaticBlock;

//This program illustrates the static method does not have access to instance variable

public class StaticMethod1 {
	
	static int a = 4;
	
	int b = 5;
	
	void simpleDisplay() {
		System.out.println("a : "+a);
		System.out.println("b : "+b);
		
	}
	
	static void staticDisplay() {
		System.out.println(a);
	}

	public static void main(String args[])
	{
		StaticMethod1 sm = new StaticMethod1();
		sm.simpleDisplay();
		
		staticDisplay();
	}
}
