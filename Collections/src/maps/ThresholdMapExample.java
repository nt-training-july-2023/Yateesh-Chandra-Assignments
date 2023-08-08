package maps;

import java.util.HashMap;
import java.util.Map;

public class ThresholdMapExample {

	private Map<String, Integer> map;
	private int thresholdSize;
	
	public ThresholdMapExample(int thresholdSize) {
		// TODO Auto-generated constructor stub
		this.map = new HashMap<>();
		this.thresholdSize = thresholdSize;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int threshold = 3;
		ThresholdMapExample obj = new ThresholdMapExample(threshold);
		
		obj.addElement("Element 1 ", 10);
		obj.addElement("Element 2 ", 20);
		obj.addElement("Element 3 ", 30);

	}
	private void addElement(String key, int value) {
		// TODO Auto-generated method stub
		map.put(key, value);
		checkAndClearThreshold();
	}
	private void checkAndClearThreshold() {
		// TODO Auto-generated method stub
		System.out.println("Current elements in the map : ");
		
		for(Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey()+": " + entry.getValue());
		}
		
		if(map.size() >= thresholdSize) {
			System.out.println("Map Reached the threshold size. Clearing the map.");
			map.clear();
		}
	}

}

