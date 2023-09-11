
// Class for a product from the store that has a name and a price
public class Product {

	//Attribute
	private String name;
	private int price;
	
	//Constructors
	public Product (String name, int price) {
		this.name=name;
		this.price=price;
	}
	
	//Methods
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if (!name.equals(""))
			this.name=name;
	}
	public int getPrice() {
		return this.price;
	}
	public void setPrice(int price) {
		if(price!=0)
			this.price=price;
	}
}
