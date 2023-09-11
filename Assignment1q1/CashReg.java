

//Class for a cash register, will retain balance and during a perches all the details of it, when closing the bill will update balance
import java.util.ArrayList;

public class CashReg {

	//Attribute
	private int balance;
	private ArrayList<BillRow>row=new ArrayList<BillRow>();
	//Constructors
	public CashReg() {
		this.balance=0;
	}
	public CashReg(int balance) {
		this.balance=balance;
	}
	//Methods
	//Adding a row with a dynamic array
	public void addRow(Product proName , int amount) {
		row.add(new BillRow(proName, amount));
	}
	//return the information about the customer perches
	public String toString() {
		String sum = "";
		for (int i =0; i<row.size();i++) {
			sum+=row.get(i);
			sum+="\n";
		}
		return sum;
	}
	//return sub summary of perches for customer
	public int subSum() {
		int sum=0;
		for (int i =0; i<row.size();i++) {
			sum+=row.get(i).getTotalPrice();
		}
		return sum;
	}
	//Close the bill, return the change according to amount paid and total price, and clear the billRow array
	public int closeBill(int pay) {
		int sum=this.subSum();
		row.clear();
		this.balance+=sum;
		return pay-sum;
	}
	//Get how much money in the cash register
	public int getBalance() {
		return this.balance;
	}
}
