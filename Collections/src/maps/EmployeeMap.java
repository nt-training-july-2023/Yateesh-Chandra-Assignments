package maps;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EmployeeMap {

	private Map<Integer, String> employeeMap;
	
	public EmployeeMap(){
		employeeMap = new HashMap<>();
	}
	
	public void addEmployee(int employeeId, String employeeName) {
		employeeMap.put(employeeId, employeeName);
	}
	
	public void printEmployeeIdByPartialname(String partialName) {
		for(Map.Entry<Integer, String> entry: employeeMap.entrySet()) {
			if(entry.getValue().contains(partialName)) {
				System.out.println("Employee ID : " + entry.getKey());
			}
		}
	}
	
	public static void main(String args[]) {
		EmployeeMap emp = new EmployeeMap();
		emp.addEmployee(1, "Yateesh");
		emp.addEmployee(2, "Chandra");
		emp.addEmployee(3, "Shiva");
		emp.addEmployee(4, "Keshava");
		
		Scanner s = new Scanner(System.in);
		System.out.println("Enter a name to check the EmployeeId : ");
		String partialName = s.nextLine();
		System.out.println("Employee Ids for Employees with the name  containing " +partialName + ":");
		emp.printEmployeeIdByPartialname(partialName);
		s.close();
		
	}
}
