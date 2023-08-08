package AnonymousClass;

public class AnonymousInnerClassOverride {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MyInterface obj = new MyInterface() {
		
		@Override
		public void display() {
			System.out.println("Method Overridden - First instance");
		}

		};
		
		obj.display();
		
		MyInterface obj2 = new MyInterface() {
			
			@Override
			public void display() {
				System.out.println("Method overridden - Second instance");
			}
		};
		
		obj2.display();
		
		MyInterface obj3 = new MyInterface() {
			
			@Override
			public void display() {
				System.out.println("Method overridden - Third instance");
			}
		};
		
		obj3.display();
}
}
