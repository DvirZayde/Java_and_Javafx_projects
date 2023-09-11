
public class Monitor {

	private int[][] a;
	private int[][] b;
	private int total=0;
	
	public Monitor(int[][] a,int[][] b) {
		this.a=a;
		this.b=b;
	}
	public int getLength() {
		return a[0].length;
	}
	public int getCellA(int i, int j) {
		return a[i][j];
	}
	public int getCellB(int i, int j) {
		return b[i][j];
	}
	public synchronized void printCell(int sum, int i, int j) {
		while ((i*b[0].length)+j>total){
			try {
				wait();
			}
			catch (InterruptedException e){e.printStackTrace(); }
		}
		if (j==0) 
			System.out.print("\n");
		System.out.print(sum+"\t");
		total++;
		notifyAll();
	}
	
}
