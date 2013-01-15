import java.util.*;

public class TeamLeader extends Employee {

	/*
	*	Variables
	*/
	
	// Group of employees on this Team Leader's team
	private ArrayList<Developer> myTeam = new ArrayList<Developer>();
	
	// This team leader's manager
	private Manager myManager;
	
	public TeamLeader(int teamNumber, Manager manager, Firm firm) {
		super(firm);
		this.teamNumber = teamNumber;
		this.myManager = manager;
		
		// Add self to manager's list of team leaders
		myManager.addTeamLeader(this);
		setName("Team Leader " + getTeamNumber());
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
			logAction("asked manager for help with Developer question.");
			askManagerQuestion();
		}
		else{
			logAction("answered developer's question.");		
		}

	}
	
	// meeting with meam members
	public void startTeamMeeting(){
		theFirm.getConfRoom().enterRoom();
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