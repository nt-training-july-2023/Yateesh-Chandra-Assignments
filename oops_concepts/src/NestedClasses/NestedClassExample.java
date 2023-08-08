package NestedClasses;

public class NestedClassExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

			     // Creating instances of nested classes
			     OuterClass outer = new OuterClass();
			     OuterClass.Inner inner = outer.new Inner();
			     OuterClass.StaticNested staticNested = new OuterClass.StaticNested();
			     
			     // Calling methods of nested classes
			     inner.display();
			     staticNested.display();
			     
			     // Calling methods with method-local inner class and anonymous inner class
			     outer.methodWithLocalInner();
			     outer.methodWithAnonymousInner();
			     
			     // Creating instance of static nested interface
			     OuterClassInterface.NestedInterface nestedInterface = new OuterClassInterface.NestedInterface() {
			         @Override
			         public void display() {
			             System.out.println("Static Nested Interface");
			         }
			     };
			     
			     // Calling method of static nested interface
			     nestedInterface.display();
			 }
			}
