import java.util.*;

public class TeamLeader extends Employee {

	/*
	*	Variables
	*/
	
	// Group of employees on this Team Leader's team
	private ArrayList<Developer> myTeam;
	
	// This team leader's manager
	private Manager myManager;
	
	/**
	*	Ask a question to your manager
	*/
	public void askManagerQuestion(){
		manOffice.enterforQuestion();
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
			askManagerQuestion();
		}
	}
	
	// meeting with meam members
	public void startTeamMeeting(){
		confRoom.enterRoom();
	}
	
	// meeting with other team leads and manager
	public void goToStandup(){
		manOffice.enterStandupMeeting();
	}
	
}