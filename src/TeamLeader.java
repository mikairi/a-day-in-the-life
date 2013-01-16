import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TeamLeader extends Employee {

	/*
	*	Variables
	*/
	
	// Group of employees on this Team Leader's team
	private ArrayList<Developer> myTeam = new ArrayList<Developer>();
	
	private CyclicBarrier smallTeamConference = new CyclicBarrier(3);
	
	// This team leader's manager
	private Manager myManager;
	
	public TeamLeader(int teamNumber, Manager manager, Firm firm) {
		super(firm);
		this.teamNumber = teamNumber;
		this.employNumber = 1;
		this.myManager = manager;
		
		// Add self to manager's list of team leaders
		myManager.addTeamLeader(this);
		setName("Developer " + getTeamNumber() + getEmployNumber());
	}
	
	public CyclicBarrier getSmallTeamConference() {
		return smallTeamConference;
	}
	
	public synchronized void addTeamMember(Developer member) {
		myTeam.add(member);
	}
	
	/**
	*	Ask a question to your manager
	*/
	public void askManagerQuestion(){
		theFirm.getManOffice().enterforQuestion();
		hasQuestion = false;
	}
	
	/**
	*	Answer a question from your team
	*/
	public void answerTeamQuestion(){
		Random randGen = new Random();
		// If true, team lead and the member go to ask the manager.
		// Otherwise automatically answer and naturally return to work.
		// TODO: The team member also needs to go to the manager.
		if (randGen.nextBoolean()) {
			logAction("asks manager for help with Developer question.");
			askManagerQuestion();
		}
		
		else{
			logAction("answered developer's question.");		
		}
	}
	
	// meeting with team members
	public void startTeamMeeting(){
		while(smallTeamConference.getNumberWaiting() < 3)
		{
			try {
				sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		while(!theFirm.getConfRoom().canEnter()) {
			try {
				sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		logAction("starts team meeting.");
		
		try {
			sleep(150);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		logAction("ends team meeting.");
		
		theFirm.getConfRoom().leaveRoom();
		
		try {
			// cyclic half-barrier
			smallTeamConference.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
	
	// meeting with other team leads and manager
	public void goToStandup(){
		theFirm.getManOffice().enterStandupMeeting();
	}

	public void run(){
		sleepUntil( 480);
		goToWork();
	}
	
}
