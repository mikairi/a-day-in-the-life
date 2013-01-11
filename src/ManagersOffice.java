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
public class ManagersOffice {

	private CyclicBarrier standupMeeting = new CyclicBarrier(4);
	
	public synchronized void enterStandupMeeting(){
		numEmployees++;
		try {
			standupMeeting.await();
			wait(150);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}catch (BrokenBarrierException e1) {
			e1.printStackTrace();
		}
		numEmployees--;
	}
	
	//-------------------------------------------------------------------
	
	private int numEmployees = 0;
	private LinkedList<Integer> waitingTeams = new LinkedList<Integer>();
	private boolean managerIsIn = false;
	
	/**
	 * Requests to enter room to ask question, and waits if cant
	 */
	public synchronized void enterforQuestion(){
		Employee employee = (Employee)Thread.currentThread();
		
		//if the team number isnt registered, register it.
		if(!waitingTeams.contains(employee.getTeamNumber())){
			waitingTeams.add(employee.getTeamNumber());
		}

		//wait if the employee team number isnt same as those in room
		while(waitingTeams.peek() != employee.getTeamNumber() && managerIsIn){
			try{
				wait();
			}
			catch(InterruptedException e){
				System.err.println(e.getMessage());
			}
		}
		numEmployees++;
		//ask the question for 10 minutes
		wait(100);
		leaveRoom();
	}
	
	public synchronized void managerEnter(){
		managerIsIn = true;
	}

	public synchronized void managerLeave(){
		managerIsIn = false;
	}
	
	
	//--private methods----
	
	/**
	 * An employee leaves the room. If there are none left in the room then
	 * other teams can take over.
	 */
	private synchronized void leaveRoom(){
		numEmployees--;
		if(numEmployees == 0){
			waitingTeams.remove();
		}
		notifyAll();
	}
}