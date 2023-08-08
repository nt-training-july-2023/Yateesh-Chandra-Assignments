package OperatorsAndExpressions;

public class BitwiseAndShiftOperators {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int a = 5; 
	    int b = 3;
		
	    //Bitwise And     
	    int resultAND = a & b; 
	    System.out.println("Bitwise AND result: " + resultAND);
	       
	    //Bitwise Or
	    int resultOR = a | b; 
	    System.out.println("Bitwise OR result: " + resultOR);
	    
	    //Bitwise XOR
	    int resultXOR = a ^ b; 
	    System.out.println("Bitwise XOR result: " + resultXOR);
	    
	    //Bitwise Not
	    int resultNOT = ~a; 
	    System.out.println("Bitwise NOT result of a: " + resultNOT);
	    
	    //Bitwise Left Shift
	    int resultBLS = a << 2;
	    System.out.println("Bitwise Left Shift result of a: " + resultBLS);
	    
	    //Bitwise Left Shift
	    int resultBRS = a >> 2;
	    System.out.println("Bitwise Right Shift result of a: " + resultBRS);
	    
	    //Bitwise Unsigned Right Shift	
	    int resultBURS = a >>> 2;
	    System.out.println("Bitwise Unsigned Right Shift result of a: " + resultBURS);
    }
}
