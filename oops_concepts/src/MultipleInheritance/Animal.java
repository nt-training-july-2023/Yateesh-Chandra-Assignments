package MultipleInheritance;

public class Animal implements AnimalEat, AnimalSleep {

	@Override
	public void sleep() {
		System.out.println("The animal is literally sleeping");
		
	}

	@Override
	public void eat() {
		System.out.println("The animal is eating");
		
	}

}
