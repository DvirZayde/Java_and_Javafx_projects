import java.io.Serializable;
import java.util.ArrayList;

public class MenuList implements Serializable{

	private ArrayList<Dish> firstList = new ArrayList<Dish>();
	private ArrayList<Dish> mainList = new ArrayList<Dish>();
	private ArrayList<Dish> dessertList = new ArrayList<Dish>();
	private ArrayList<Dish> drinkList = new ArrayList<Dish>();
	
	//Note: some of the Method are not use in the project but are here for future use
	public void addFirst(Dish d) {
		firstList.add(d);
	}
	public void addMain(Dish d) {
		mainList.add(d);
	}
	public void addDessert(Dish d) {
		dessertList.add(d);
	}
	public void addDrink(Dish d) {
		drinkList.add(d);
	}
	public ArrayList<Dish> getFirstList() {
		return firstList;
	}
	public void setFirstList(ArrayList<Dish> firstList) {
		this.firstList = firstList;
	}
	public ArrayList<Dish> getMainList() {
		return mainList;
	}
	public void setMainList(ArrayList<Dish> mainList) {
		this.mainList = mainList;
	}
	public ArrayList<Dish> getDessertList() {
		return dessertList;
	}
	public void setDessertList(ArrayList<Dish> dessertList) {
		this.dessertList = dessertList;
	}
	public ArrayList<Dish> getDrinkList() {
		return drinkList;
	}
	public void setDrinkList(ArrayList<Dish> drinkList) {
		this.drinkList = drinkList;
	}
	
	
}
