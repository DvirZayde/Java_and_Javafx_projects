//class of smoke alarm as describe in the exercise 
public class Smoke extends Alarm {
	//Attribute
	private String operator;
	//constructor of smoke that throws Exception
	public Smoke (String address, String operator)throws BadAlarm{
		super(address);
		this.operator=operator;
	}
	//print object's characteristics
	public void action() {
		super.action();
		System.out.println ("	operator is:"+this.operator);
	}

}
