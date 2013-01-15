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
			System.out.println( Thread.getName() + "has to ask manager " +
					"for help with Developer question.");
			askManagerQuestion();
		}
		else{
			// 10 minutes to answer a question
			try {
				sleep(100);
				System.out.println( Thread.getName() + "answers Developer question.");
			}	 
			catch (InterruptedException e) {
				e.printStackTrace();
			}			
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
	
	void goToWork() {
		System.out.println("Team Leader " + getTeamNumber() + " arrived to work.");
		super.goToWork();
	}
	
	void goHome() {
		System.out.println("Team Leader " + getTeamNumber() + " has left the work place.");
		super.goHome();
	}
	
	void goToLunch() {
		System.out.println("Team Leader " + getTeamNumber() + " went to lunch.");
		super.goToLunch();
	}
	
	void returnToWork() {
		System.out.println("Team Leader  " + getTeamNumber() + " is now working.");
		super.returnToWork();
	}
	
}