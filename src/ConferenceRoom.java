import java.util.LinkedList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/*
 * The point of this program is to simulate a room that regulates when a 
 * team can come in to meet.
 * 
 * @Author: Andrew Mueller
 * @Date: 1/9/2013
 */
public class ConferenceRoom {
	
	private int numEmployees = 0;
	private LinkedList<Integer> waitingTeams = new LinkedList<Integer>();
	private CyclicBarrier startMeeting = new CyclicBarrier(4);
	
	/**
	 * Requests to enter room, and waits if cant
	 * 
	 * Employees request to enter the conference room for a team meeting.
	 * First come first serve, the first teams to arrive enter first. Before
	 * letting people in it checks to see if those in the room are in the same
	 * team. When all employees arrive, the meeting starts 
	 */
	public synchronized void enterRoom(){
		Employee employee = (Employee)Thread.currentThread();
		
		//if the team number isnt registered, register it.
		if(!waitingTeams.contains(employee.getTeamNumber())){
			waitingTeams.add(employee.getTeamNumber());
		}

		//wait if the employee team number isnt same as those in room
		while(waitingTeams.peek() != employee.getTeamNumber()){
			try{
				wait();
			}
			catch(InterruptedException e){
				System.err.println(e.getMessage());
			}
		}
		numEmployees++;
		try {
			//make sure all are in before starting meeting
			startMeeting.await();
			//have the meeting for 15 minutes
			wait(150);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * An employee leaves the room. If there are none left in the room then
	 * other teams can take over.
	 */
	public synchronized void leaveRoom(){
		numEmployees--;
		if(numEmployees == 0){
			waitingTeams.remove();
		}
		notifyAll();
	}
}