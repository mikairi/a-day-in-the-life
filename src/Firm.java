import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Firm keeps track and provide access to firm's resources.
 */
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

	/**
	 * Start the clock and start the day.
	 */
	public void startDay() {
		clock.start();
	}
	
	/**
	 * Employee leaves the firm.
	 */
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
