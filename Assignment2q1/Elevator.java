//class of elevator alarm as describe in the exercise 
public class Elevator extends Alarm{

	//Attribute
	private int floor;
	//constructor
	public Elevator(String address, int floor)throws BadAlarm {
		super(address);
		this.floor=floor;
	}
	//print object's characteristics
	public void action() {
		super.action();
		System.out.println ("	floor is:"+this.floor);
	}
	public void reset() {
		this.floor=0;
	}
}
