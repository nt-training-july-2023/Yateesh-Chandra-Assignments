package StringCollection;

import java.util.*;

public class StringCollection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Set<String> stringSet = new LinkedHashSet<>();
		
		stringSet.add("Apple");
		stringSet.add("Banana");
		stringSet.add("Date");
		stringSet.add("Cherry");
		stringSet.add("Apple");
		stringSet.add("Pomegranate");
		
		System.out.println("Elements in the string collection : ");
		for(String element : stringSet) {
			System.out.println(element);
		}
		
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the element to check whether the element is present in the list or not : ");
		String ele = s.nextLine();
		if(stringSet.contains(ele)) {
			System.out.println("Hurray..! " +ele + " is found in the Collection..! ");
		}
		else {
			System.out.println("Sorry..! " +ele + " is not found in the Collection..! ");
		}
		
		System.out.println("Enter the element to remove from the list : ");
		String element = s.nextLine();
		if(stringSet.contains(element)==true) {
		
			stringSet.remove(element);
			System.out.println("element is present in the Collection "+ element + " is removed successfully..!");
		}
		
		else {
			System.out.println("Element is not found in the Collection..!");
		}
		s.close();
		
	}

}
