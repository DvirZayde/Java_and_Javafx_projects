import java.util.Random;
//DataBase is an object the retain false months temperature history of 5 years 
public class DataBase {
	
	private final int MONTHS = 12;
	private final int YEARS = 5;
	private final int MAX_COLOM_HEIGHT = 200;
	
	//Attribute
	private int temp[][]= new int[YEARS][MONTHS];
	//Construct DataBase object
	public DataBase() {
		Random r = new Random();
		for (int i=0; i<YEARS; i++) {
			for (int j=0;j<MONTHS;j++) {
				this.temp[i][j]=r.nextInt(MAX_COLOM_HEIGHT);
			}
		}
	}
	//Get specific months temp according to month and year input
	public int getTemp(int year, int month) {
		return this.temp[year][month];
	}
}
