import java.io.Serializable;

public class Dish implements Serializable {

	private String name;
	private String type; //Main, first dessert or a drink
	private double price;
	public Dish(String name, String type, double price) {
		this.name = name;
		this.type = type;
		this.price = price;
	}
	//Note: some of the Method are not use in the project but are here for future use
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
