

public class main {

	public static void main(String[] args) {
		//Input is set in the code, can easily change to input from user
		Product p1 = new Product("milk", 5);
		Product p2 = new Product("eggs", 11);
		Product p3 = new Product("bread", 7);
		CashReg c = new CashReg(145);
		c.addRow(p1, 2);
		c.addRow(p3, 6);
		c.addRow(p2, 8);
		//using the different methods
		System.out.println("Hellow, the initial balance in the cash register is:"+c.getBalance());
		System.out.println("Here is the elaboration of your purches:\n");
		System.out.println(c);
		System.out.println("And the total price of your purches is:"+c.subSum());
		System.out.println("Thank you for your purches, you paid 150, Change is:"+c.closeBill(150));
		System.out.println("The balance in the cash register is:"+c.getBalance());
	}
}
