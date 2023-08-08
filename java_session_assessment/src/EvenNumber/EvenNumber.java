package EvenNumber;
import java.util.*;

public class EvenNumber {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		try {
			
			System.out.println("Enter the number to check whether the number is even or not : ");
			int num = s.nextInt();
			
			validEvenNum(num);
			System.out.println("The number "+num+" is even");
		}
		catch(NotEvenNumberException e) {
			System.out.println(e.getMessage());
		}
		s.close();
	}

	private static void validEvenNum(int num) throws NotEvenNumberException {
		// TODO Auto-generated method stub
		if(num < 0) {
			throw new NotEvenNumberException("A negative number not allowed..!");
		}
		
		else if(num%2 != 0) {
			throw new NotEvenNumberException("It is not even number, It is odd..!");
		}
	}

}
