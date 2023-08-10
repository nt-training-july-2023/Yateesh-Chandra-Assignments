package Files;

public class Smartphone {

	class SmartPhone {
	    // Define the SmartPhone class here
	    private String brand;
	    private double price;

	    public SmartPhone() {
	    	
	    }
	    
	    public SmartPhone(String brand, double price) {
			super();
			this.brand = brand;
			this.price = price;
		}

		// Constructors, methods, and other members go here

	    public String getBrand() {
			return brand;
		}

		public void setBrand(String brand) {
			this.brand = brand;
		}

		public double getPrice() {
	        return price;
	    }

	    public void setPrice(double price) {
	        this.price = price;
	    }
	}
}
