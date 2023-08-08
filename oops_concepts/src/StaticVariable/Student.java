package StaticVariable;

public class Student {

	int rollNo;
	String name;
	static String college = "Andhra University";
	
	Student(int r, String n){
		rollNo = r;
		name = n;
	}
	
	void display() {
		System.out.println("Roll Number is : " + rollNo + ". His name is :" + name + " studying in "+college);
	}
}
