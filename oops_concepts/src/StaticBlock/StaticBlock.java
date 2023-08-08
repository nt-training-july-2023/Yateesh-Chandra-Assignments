package StaticBlock;

public class StaticBlock {

	static int i = 8;

	public void stat() {
		System.out.println("The value of i is : " + i);
	}

	static int j;

	/*
	 * static { i = 10; System.out.println("Static Block called"); }
	 */

}
