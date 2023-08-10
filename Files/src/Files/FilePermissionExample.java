package Files;

import java.io.FilePermission;
import java.security.PermissionCollection;

public class FilePermissionExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "D:\\test.txt";
		FilePermission file1 = new FilePermission("D:\\-","read");
		
		PermissionCollection permission = file1.newPermissionCollection();
		permission.add(file1);
		
		FilePermission file2 = new FilePermission(str,"write");
		permission.add(file2);
		
		if(permission.implies(new FilePermission(str, "read,write"))) {
			System.out.println("Read, Write Permission is granted for the path..!"+str);
		}
		
		else {
			System.out.println("No Read, Write permission is granted"+str);
		}

	}

}
