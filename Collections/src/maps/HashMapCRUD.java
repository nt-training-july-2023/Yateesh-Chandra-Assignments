package maps;

import java.util.*;

public class HashMapCRUD {
	private static Map<Integer, String> hashMap = new HashMap<>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		addEntry(1, "Yateesh");
		addEntry(2, "Shiva");
		addEntry(3, "Keshava");
		addEntry(4, "Praveen");
		System.out.println("HashMap after adding the elements : " + hashMap);
		
		updateEntry(2, "Parameshwara");
		System.out.println("HashMap after updating an element : "+ hashMap);
		
		int keyToGet = 1;
		System.out.println("Element with key : "+keyToGet + ": "+getEntry(keyToGet));
		
		int keyToRemove = 3;
		removeEntry(keyToRemove);
		System.out.println("HashMap after removing an element : " + hashMap);
	}

	private static void removeEntry(int key) {
		// TODO Auto-generated method stub
		hashMap.remove(key);
	}

	private static void updateEntry(int i, String string) {
		// TODO Auto-generated method stub
		if(hashMap.containsKey(i)){
			hashMap.put(i, string);
		}
	}

	private static String getEntry(int key) {
		// TODO Auto-generated method stub
		return hashMap.getOrDefault(key,"Key not Found");
	}

	private static void addEntry(int i, String string) {
		// TODO Auto-generated method stub
		hashMap.put(i, string);
	}

}

