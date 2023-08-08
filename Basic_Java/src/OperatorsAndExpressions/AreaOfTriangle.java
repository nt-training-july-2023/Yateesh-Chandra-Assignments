package OperatorsAndExpressions;
import java.util.*;

public class AreaOfTriangle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner s = new Scanner(System.in);
		System.out.println("Enter the Base of the Traingle : ");
		double base = s.nextDouble();
		
		System.out.println("Enter the Height of the Traingle : ");
		double height = s.nextDouble();
		
		double area = 0.5 * base * height;
		
		System.out.println("Area of the Given Triangle given Dimensions : " + area);
		
		s.close();
	}

}
