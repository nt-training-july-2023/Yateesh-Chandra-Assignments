package Arrays;

import java.util.Scanner;

public class AverageOfElements {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner s = new Scanner(System.in);
		System.out.println("Enter the number of elements in the array:");
		int num = s.nextInt();
		System.out.println("Enter the elements into the array: ");
		int a[] = new int[num];
		
		for (int i = 0; i<num; i++)
		{
			a[i] = s.nextInt();
		}
		
		System.out.println("The array is : ");
		for(int i = 0; i<num; i++)
		{
		System.out.println(a[i]);
		}
		
		int sum = 0;
		for (int i = 0;i<num;i++)
		{
			sum = sum + a[i];
		}
		
		System.out.println("The sum of the elements in the array is : " + sum);
		s.close();
	}

}
