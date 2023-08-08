package Variables;

public class Literals {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Using float literal for integer data type
        //int myInt = 10.5f; // This will result in a compilation error showing that implicit narrow conversions are not possible
        
        //byte myByte = 200; //This will show that it is not possible to assign 200 as a byte value since the range of the byte is between -128 and 127
        
        int myInt = 'A'; //This will result the ASCII value of the character
        System.out.println(myInt);
	}

}
