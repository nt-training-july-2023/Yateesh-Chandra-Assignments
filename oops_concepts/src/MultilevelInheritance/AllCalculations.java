package MultilevelInheritance;

public class AllCalculations extends MyCalculation{

	public void division(int a, int b) {
		int remainder = a % b;
		int quotient = a/b;
		
		System.out.println("The Remainder is : " + remainder);
		System.out.println("The Quotient is : " + quotient);
	}
}
