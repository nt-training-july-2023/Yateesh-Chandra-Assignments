package StaticVariable;

import java.util.Scanner;

public class StudentMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner s = new Scanner(System.in);
		
		Student s1 = new Student(1, "Seshu");
		Student s2 = new Student(10, "Prakash");
		
		s1.display();
		s2.display();
		
		s.close();
	}

}
