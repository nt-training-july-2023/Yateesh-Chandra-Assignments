package Files;

import java.io.ByteArrayInputStream;

public class ByteArrayInputStreamExample {

public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
	byte[] buf = {35,36,37,38};
	ByteArrayInputStream byt = new ByteArrayInputStream(buf);
	
	int k = 0;
	while((k = byt.read())!= -1) {
		char ch = (char)k;
		System.out.println("ASCII value of characters : " + k +"Special character is : "+ch);
	}
	}

}
