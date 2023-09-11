import java.io.Serializable;
import java.util.Objects;

public class Date implements Serializable {

	private int day;
	private int month;
	private int year;
	
	public Date(int day, int month, int year) {
		super();
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public int hashCode() {
		return Objects.hash(day, month, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			System.out.println("Null pointer");
			return false;
		}
		if (!(obj instanceof Date)) {
			System.out.println("Object is not instance of Date");
			return false;
		}	
		Date other = (Date) obj;
		return day == other.day && month == other.month && year == other.year;
	}
	
	
	
}
