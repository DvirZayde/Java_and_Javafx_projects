//class of fire alarm as describe in the exercise 
public class Fire extends Smoke {

	//Attribute
	private Boolean active;
	//constructor
	public Fire(String address, String operator)throws BadAlarm {
		super(address, operator);
		this.active=true;
	}
	//print object's characteristics
	public void action() {
		this.active=false;
		super.action();
	}
	
}
