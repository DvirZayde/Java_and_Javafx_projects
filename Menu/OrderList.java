import java.io.Serializable;
import java.util.ArrayList;

public class OrderList implements Serializable {

	 private ArrayList<Dish> order = new ArrayList<Dish>();
	 private ArrayList<Integer> amount = new ArrayList<Integer>();
	 
	//Note: some of the Method are not use in the project but are here for future use
	 
	public void addDish(Dish d, int amount) {
		this.order.add(d);
		this.amount.add(amount);
	}
	
	public void clearBill() {
		this.order.clear();
		this.amount.clear();
	}

	public ArrayList<Dish> getOrder() {
		return order;
	}

	public void setOrder(ArrayList<Dish> order) {
		this.order = order;
	}

	public ArrayList<Integer> getAmount() {
		return amount;
	}

	public void setAmount(ArrayList<Integer> amount) {
		this.amount = amount;
	}
	
	
}
