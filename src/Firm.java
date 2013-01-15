public class Firm {
	private final FirmClock clock;
	private final ConferenceRoom confRoom;
	private final ManagersOffice manOffice;
	
	public Firm() {
		clock = new FirmClock();
		confRoom = new ConferenceRoom();
		manOffice = new ManagersOffice();
	}
	
	public FirmClock getClock() {
		return clock;
	}

	public ConferenceRoom getConfRoom() {
		return confRoom;
	}

	public ManagersOffice getManOffice() {
		return manOffice;
	}

	public void startDay() {
		clock.start();
	}
}
