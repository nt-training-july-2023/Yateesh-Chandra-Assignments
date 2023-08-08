package lambdaFunctions;

interface Math{
	int add(int a, int b);
}

public class AdditionUsingLambda {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Math obj = (a, b) ->{ return(a+b);};
			

obj.add(3, 5);
}
}
