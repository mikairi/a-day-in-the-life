import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Firm {
	private final FirmClock clock;
	private final ConferenceRoom confRoom;
	private final ManagersOffice manOffice;
	private CyclicBarrier goneHome = new CyclicBarrier(13);
	
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
	
	public CyclicBarrier getGoneHome() {
		return goneHome;
	}

	public void startDay() {
		clock.start();
	}
	
	public void leaveFirm() {
		try {
			goneHome.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
}
