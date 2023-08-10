package Files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InvertingContent {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			
			File data = new File("D:\\test.txt");
			FileInputStream fis = new FileInputStream (data);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			List<String> lines = new ArrayList<>();
			String line; 
			while((line = br.readLine())!= null) {
				lines.add(line);
			}
			br.close();
			
			FileWriter file = new FileWriter("D:\\ot.txt");
			PrintWriter printer = new PrintWriter(file);
			
			Collections.reverse(lines);
			for(String l: lines) {
				printer.print(l);
			}
				
			System.out.println("The items are successfully inverted from test.txt to ot.txt");
			
			printer.close();
			fis.close();
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

}
