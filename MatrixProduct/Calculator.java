
public class Calculator extends Thread {
	private Monitor m;
	private int row;
	private int column;
	
	public Calculator (Monitor m, int row, int column) {
		this.m=m;
		this.row=row;
		this.column=column;
	}
	public void run() {
		super.run();
		int len=m.getLength();
		int sum=0;
		for (int i=0; i<len;i++) {
			sum+=(m.getCellA(row, i)*m.getCellB(i, column));
		}
		m.printCell(sum, row, column);
		
	}
	

}
