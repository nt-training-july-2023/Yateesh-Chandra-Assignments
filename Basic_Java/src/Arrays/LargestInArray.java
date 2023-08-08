package Arrays;

import java.util.Scanner;

public class LargestInArray {

	public static void main(String args[])
	{
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
		
		int large = -9999;
		for (int i = 0;i<num;i++)
		{
			if(a[i]>large) {
				large = a[i];
			}
		}
		
		System.out.println("The largest element in the array is : "+large);
		s.close();
	}
}
