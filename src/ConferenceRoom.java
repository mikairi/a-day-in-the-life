import java.util.LinkedList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/*
 * The point of this program is to simulate a room that regulates when a 
 * team can come in to meet.
 */
public class ConferenceRoom {
	
	private int numEmployees = 0;
	private LinkedList<Integer> waitingTeams = new LinkedList<Integer>();
	private CyclicBarrier startMeeting = new CyclicBarrier(4);
	private CyclicBarrier endOfDayMeeting = new CyclicBarrier(12); //12 for debugging purpose
	private boolean isEmpty = true;
		
	public synchronized boolean canEnter() {
		if(isEmpty) {
			isEmpty = false;
			return true;
		}
		else {
			return false;
		}
	}
	
	public void leaveRoom() {
		isEmpty = true;
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