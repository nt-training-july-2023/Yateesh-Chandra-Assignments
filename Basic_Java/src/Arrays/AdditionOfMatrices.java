package Arrays;

import java.util.Scanner;

public class AdditionOfMatrices {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner s = new Scanner(System.in);
		System.out.println("Enter the number of the Rows : ");
		int r = s.nextInt();
		System.out.println("Enter the number of the Columns : ");
		int c = s.nextInt();
		
		int a[][] = new int[r][c];
		System.out.println("Enter the elements into matrix A:");
		for(int i = 0; i<r; i++)
		{
			for(int j = 0; j<c; j++) {
				a[i][j] = s.nextInt();
			}
		}
		
		int b[][]= new int[r][c];
		System.out.println("Enter the elements into matrix B:");
		for(int i = 0; i<r; i++)
		{
			for(int j = 0; j<c; j++) {
				b[i][j] = s.nextInt();
			}
		}    
		
		    
		//creating another matrix to store the sum of two matrices    
		int k[][]=new int[r][c];  //3 rows and 3 columns  
		    
		System.out.println("The Addition of the matrix is : ");
		//adding and printing addition of 2 matrices    
		for(int i=0; i<r; i++){    
		for(int j=0; j<c; j++){    
		k[i][j]=a[i][j]+b[i][j];      
		System.out.print(k[i][j]+" ");    
		}    
		System.out.println();//new line    
		}
		
		s.close();
	}

}
