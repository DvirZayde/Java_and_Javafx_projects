import java.util.Date;

public abstract class Alarm {

	//Attribute
	protected Date time;
	protected String address;
	//constructor
	//Note:I Decided to implement the Exception in 'Alarm' class since all of the Classes has address and it made more sense
	public Alarm (String address)throws BadAlarm {
		if (address==null)
			throw new BadAlarm();
		this.time=new Date();
		this.address=address;
	}
	public void action() {
		System.out.print("Activated at:"+this.time+"	address is:"+ this.address);
	}
	
}

