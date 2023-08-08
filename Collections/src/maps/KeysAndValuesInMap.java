package maps;

import java.util.HashMap;
import java.util.Map;

public class KeysAndValuesInMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap<>();
		map.put("Sundar", 25);
		map.put("Mahesh", 30);
		map.put("Suresh", 35);
		map.put("Ramesh", 40);
		
		System.out.println("All Keys in the map : ");
		for(String key : map.keySet()) {
			System.out.println(key);
		}
		
		System.out.println("All Values in the map : ");
		for(Integer value : map.values()) {
			System.out.println(value);
		}
	}
}
