package Files;

import java.io.File;

public class FilesList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		File directoryPath = new File("D:");
		String contents[] = directoryPath.list();
		
		System.out.println("List of Files and Directories in the folder : ");
		for(int i = 0; i<contents.length; i++) {
			System.out.println(contents[i]);
		}

	}

}
