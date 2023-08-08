package Classes;

public class InnerClassesMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		InnerClasses myobj1 = new InnerClasses();
		InnerClasses.OuterClass myobj2 = myobj1.new OuterClass();
		
		System.out.println(myobj1.x + myobj2.y);
	}

}
