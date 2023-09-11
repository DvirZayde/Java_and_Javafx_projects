
public class SodukoCheck {

	//Attributes
	private char matrix[][];//A char matrix that helps us with the checks
	private int size;//keeps the number of cells in a row
	//Constructor
	public SodukoCheck(int size) {
		this.matrix = new char [size][size];
		this.size=size;
	}
	//The method gets coordinate and a digit and check the row, column and cube,
	//if input is valid will save it in the matrix
	//return true if input is valid, false otherwise
	public Boolean checkInsert(int x, int y, char enter) {
		this.matrix[x][y]=0;
		for (int i=0; i<this.size;i++) {
			if (this.matrix[x][i]==enter || this.matrix[i][y]==enter )//checks the columns and rows
				return false;
			}
			int sqrSize=(int)Math.sqrt(this.size);
			int m= ((x/sqrSize)* sqrSize);//find small cube to check
			int k = ((y/sqrSize)* sqrSize);//find small cube to check
			for (int i=0;i<sqrSize;i++) {//Check the cube
				for (int j=0;j<sqrSize;j++) {
					if (matrix[m+i][k+j]==enter)
						return false;
				}
			}
			this.matrix[x][y]=enter;
			return true;
	}
	//Clear the char matrix
	public void clear() {
		for (int i=0; i<this.size;i++) {
			for (int j=0; j<this.size;j++)
				this.matrix[i][j]=0;
		}
	}
	//Clear a cell in the matrix
	public void clearField(int x, int y) {
		this.matrix[x][y]=0;
	}
}
