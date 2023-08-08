package HierarchialInheritance;

public class HierarchialInheritanceMain {

	public static void main(String args[])
	{
		Dog d = new Dog();
		Cat c = new Cat();
		Lion l = new Lion();
		
		d.sound();
		d.light();
		c.sound();
		c.light();
		l.sound();
		l.light();
	}
}
