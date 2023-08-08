package Classes;

import java.util.Properties;

public class PropertyClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Properties prop = new Properties();
		prop.setProperty("Name", "Yateesh Chandra");
		prop.setProperty("Roll Number", "38");
		prop.setProperty("Marks", "94");
		
		String name = prop.getProperty("Name");
		String rollNo = prop.getProperty("Roll Number");
		String marks = prop.getProperty("Marks");
		
		System.out.println("Name : " + name);
		System.out.println("Roll Number : " + rollNo);
		System.out.println("Marks : " + marks	);
	}

}

