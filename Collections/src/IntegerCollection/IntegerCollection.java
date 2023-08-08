package IntegerCollection;

import java.util.*;
import java.util.stream.Collectors;

public class IntegerCollection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Integer> integerList = new ArrayList<>();
		for(int i = 1; i<=20; i++) {
			integerList.add(i*5);
		}
		
		System.out.println("Elements in reverse order : ");
		Collections.reverse(integerList);
		integerList.forEach(System.out::println);
		
		System.out.println("?\nUpdated elements with values greater than 50 : ");
		integerList.replaceAll(value -> value > 50 ? value + 5 : value);
		integerList.forEach(System.out::println);
		
		List<Integer> lessThan60List = integerList.stream().filter(value -> value < 60).collect(Collectors.toList());
		System.out.println("\nElements- less than 60 : ");
		lessThan60List.forEach(System.out::println);
	}

}
