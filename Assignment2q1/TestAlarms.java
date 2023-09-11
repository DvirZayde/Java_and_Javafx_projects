import java.util.ArrayList;

public class TestAlarms {
	//main method thats creat alarm list and enter few different kind of alarms
	public static void main(String[] args) {
		ArrayList<Alarm> alarm =new ArrayList<Alarm>();
		try {
		alarm.add(new Smoke("DAD_STREET","Bob"));
		alarm.add(new Elevator("MOM_STREET", 4));
		alarm.add(new Smoke("bro_STREET","Mike"));
		alarm.add(new Fire("SIS_STREET", "Chack"));
		//alarm.add(new Smoke (null, "Kim"));
		process(alarm);
		}catch (BadAlarm e) {
			System.out.println("address of one of the alarms is missing");
		}
		
	}

	//Method that gets alarm type array list and use it's action method, count smokes alarms and reset elevator floor
	private static void process(ArrayList<Alarm> alarms) {
		int count=0;
		for (int i=0;i<alarms.size();i++) {
			alarms.get(i).action();
			if (alarms.get(i) instanceof Smoke && !(alarms.get(i) instanceof Fire))
				count++;
			else if (alarms.get(i) instanceof Elevator)
				((Elevator)alarms.get(i)).reset();
		}
		System.out.println("The nuber of Smoke Alerts are:"+count);
	}
}
