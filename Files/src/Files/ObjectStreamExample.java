package Files;

import java.io.ObjectStreamClass;
import java.util.Calendar;

public class ObjectStreamExample {
	public static void main(String args[]) {
		ObjectStreamClass osc = ObjectStreamClass.lookup(String.class);  
		  
        // get the value field from ObjectStreamClass for integers  
        System.out.println("" + osc.getField("value"));  

        // create a new object stream class for Calendar  
        ObjectStreamClass osc2 = ObjectStreamClass.lookup(Calendar.class);  

        // get the Class instance for osc2  
        System.out.println("" + osc2.getField("isTimeSet"));  
	}

}
