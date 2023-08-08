package FileOperations;

import java.io.File;

public class ListingAllFiles {

	public static void main(String args[]) {
		File myObj = new File(".");
		String[] files = myObj.list();
		
		System.out.println("The list of Files in the current directory : ");
		for(String file : files) {
			if(file.endsWith(".txt"))
				System.out.println(file);
		}
	}
}
