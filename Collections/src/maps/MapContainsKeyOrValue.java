package maps;

import java.util.HashMap;
import java.util.Map;

public class MapContainsKeyOrValue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Map<String, Integer> map = new HashMap<>();
		map.put("Sundar", 25);
		map.put("Mahesh", 30);
		map.put("Suresh", 35);
		map.put("Ramesh", 40);
		
		String keyToCheck = "Sundar";
		if(map.containsKey(keyToCheck)) {
			System.out.println("The map contains the key : " + keyToCheck);
		}
		else {
			System.out.println("The map does not contain the key : " + keyToCheck);
		
		}
		
		int valueToCheck = 30;
		if(map.containsValue(valueToCheck)) {
			System.out.println("The map contains the key : " + valueToCheck);
		}
		else {
			System.out.println("The map does not contain the key : " + valueToCheck);
		
		}
	}

}
