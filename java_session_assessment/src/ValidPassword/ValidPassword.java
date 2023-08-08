package ValidPassword;
import java.util.*;

public class ValidPassword {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		try {
			
			System.out.println("Enter the Password : ");
			String pwd = s.nextLine();
			
			validatePassword(pwd);
			
			System.out.println("The password is valid..!");
		}
		catch(InvalidPasswordException e) {
			System.out.println(e.getMessage());
		}
		s.close();

	}

	private static void validatePassword(String pwd) throws InvalidPasswordException{
		// TODO Auto-generated method stub
		if(pwd == null || pwd.length()<8) {
			throw new InvalidPasswordException("Password must have atleast 8 Characters..!");
		}
		
		boolean containsLetters = false;
		boolean containsNumbers = false;
		
		for(char c : pwd.toCharArray()) {
			if(Character.isLetter(c)) {
				containsLetters = true;
			}
			
			else if(Character.isDigit(c)) {
				containsNumbers = true;
			}
			
			if(containsLetters && containsNumbers) {
				return ; 
			}
		}
		
		throw new InvalidPasswordException("Password must contain both letters and numbers..!");
	}

}
