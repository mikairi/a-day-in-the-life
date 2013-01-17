import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * The point of this program is to simulate a room that regulates when a team
 * can come in to meet.
 */
public class ConferenceRoom {

	private CyclicBarrier endOfDayMeeting = new CyclicBarrier(13);
	private boolean isEmpty = true;

	public synchronized boolean canEnter() {
		if (isEmpty) {
			isEmpty = false;
			return true;
		} else {
			return false;
		}
	}

	public void leaveRoom() {
		isEmpty = true;
	}

	public CyclicBarrier getEndOfDayMeetingBarrier() {
		return endOfDayMeeting;
	}

	public void attendEndOfDayMeeting() {
		try {
			endOfDayMeeting.await();
			Thread.sleep(150);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}

	}
}