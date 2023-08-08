package AccessingArrayElements;

import java.util.Scanner;

public class AccessingArrayElements {

	public static void main(String args[]) {
		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter the size of the array : ");
		int size = s.nextInt();
		
		int[] a = new int[size];
		
		System.out.println("Enter the elements into the array : ");
		for(int i = 0; i<size; i++) {
			a[i] = s.nextInt();
		}
		
		System.out.println("Enter the index to access the elements : ");
		int index = s.nextInt();
		
		try {
			int element = getElement(a, index);
			System.out.println("Element at index" +index + " is :" + element);
		}
		
		catch(IndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());
		}
		s.close();
	}
	
	private static int getElement(int[] a, int index) throws IndexOutOfBoundsException, NullPointerException{
	
		if(a == null) {
			throw new NullPointerException("Array is null. Cannot access the element..!");
		
		}
		
		if(index<0 || index> a.length ) {

			throw new IndexOutOfBoundsException("Invalid index. Index should be between 0 and "+(a.length - 1) + ".");
		}
		
		return a[index];
	}
	
}
