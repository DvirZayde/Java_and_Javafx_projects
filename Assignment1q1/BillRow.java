
//Class for a Row in the bill of a costumer
public class BillRow {

	//Attribute
	private Product proType;
	private int amount;
	private int totalPrice;
	//Constructor
	public BillRow (Product proType, int amount) {
		this.proType=proType;
		this.amount=amount;
		this.totalPrice=proType.getPrice()*amount;
	}
	//Methods
	public int getTotalPrice() {
		return this.totalPrice;
	}
	public String toString() {
		return "Product:"+this.proType.getName()+"\tAmount:"+this.amount+"\tTotal:"+this.totalPrice;
	}
}
