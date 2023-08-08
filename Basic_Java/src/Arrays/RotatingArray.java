package Arrays;

import java.util.Scanner;

public class RotatingArray {

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
		
		System.out.println("Enter the value to rotate the array : ");
		int k = s.nextInt();
		
		    k = k%num;
		    int[] temp = new int[k];
		    for(int i=0;i<k;i++)
		      temp[i] = a[i];
		    for(int i=0;i<num-k;i++)
		      a[i] = a[k+i];
		    int j = 0;
		    for(int i = num-k;i<num;i++)
		      a[i] = temp[j++];
		
		for (int i = 0;i<num;i++)
		{
			System.out.println("array after Rotation is :" + a[i]); 
		}
		s.close();
	}

}
