package NestedClasses;

//OuterClass class
class OuterClass {
 private int outerData = 10;

 // Non-static (or Instance) Inner class
 class Inner {
     void display() {
         System.out.println("Non-static Inner class: " + outerData);
     }
 }

 // Static Nested class
 static class StaticNested {
     void display() {
         System.out.println("Static Nested class");
     }
 }

 // Method-Local Inner class
 void methodWithLocalInner() {
     class LocalInner {
         void display() {
             System.out.println("Method-Local Inner class");
         }
     }

     LocalInner localInner = new LocalInner();
     localInner.display();
 }

 // Anonymous Inner class
 void methodWithAnonymousInner() {
     Runnable runnable = new Runnable() {
         @Override
         public void run() {
             System.out.println("Anonymous Inner class - Runnable");
         }
     };

     runnable.run();
 }
}

//OuterClass Interface
interface OuterClassInterface {
 // Static Nested Interface
 interface NestedInterface {
     void display();
 }
}
