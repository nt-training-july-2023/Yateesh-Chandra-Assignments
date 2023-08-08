package Encapsulation;

import java.util.Scanner;

public class StudentMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Student st = new Student();
		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter the name of the Student : ");
		String name = s.nextLine();
		st.setName(name);
		
		System.out.println("Enter the Rollnumber of the Student : ");
		int rollNo = s.nextInt();
		st.setRollNo(rollNo);
		
		System.out.println("The Name of the Student with Roll Number "+st.getRollNo()+" is : "+st.getName());
		
		s.close();
		
	}

}
