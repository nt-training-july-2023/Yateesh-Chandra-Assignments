package FinalMethodsAndClasses;

public class SampleChild extends Sample{
	
	
	/*
	 * public final void display() {
	 * System.out.println("The code is in Child Class");
	 * 
	 * //here we get that it is impossible to override the method that was declared
	 * in the Sample Parent class }
	 */

	void displayThis() {
		System.out.println("this is the Display method from the child class since the display method inherited from the Sample Parent class can not be overridden");
	}
	

}
