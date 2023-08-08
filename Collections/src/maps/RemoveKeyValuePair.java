package maps;

import java.util.HashMap;
import java.util.Map;

public class RemoveKeyValuePair {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Map<String, Integer> map = new HashMap<>();
		map.put("Sundar", 25);
		map.put("Mahesh", 30);
		map.put("Suresh", 35);
		map.put("Ramesh", 40);
		
		int valueToRemove = 30;
		
		for(Map.Entry<String, Integer> entry : map.entrySet()) {
			if(entry.getValue()== valueToRemove) {
				String keyToRemove = entry.getKey();
				map.remove(keyToRemove);
				break;
			}
		}
		
		System.out.println("Updated map after removing key-value pair with value : "+valueToRemove+": ");
		
		for(Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
	}
}
