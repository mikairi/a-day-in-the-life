import java.util.*;

public class Developer extends Employee {

	/*
	*	Variables
	*/
	
	// This developer's team leader
	private final TeamLeader myLead;
	
	public Developer(int teamNumber, int employNumber, TeamLeader lead) {
		super();
		this.teamNumber = teamNumber;
		this.employNumber = employNumber;
		this.myLead = lead;
		
		// Add self to team leader's team
		myLead.addTeamMember(this);
		setName("Developer " + getTeamNumber() + getEmployNumber());
	}
	
	/**
	*	Ask a question to your team lead
	*/
	public void askTeamLeadQuestion(){
		System.out.println("TIME Developer " + getTeamNumber() + getEmployNumber() + " asks a question.");
		
		// 10 minutes to propose a question
		try {
			sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		myLead.answerTeamQuestion();
	}
	
	/**
	*	Answer a question from your team lead
	*/
	public void answerTeamLeadQuestion(){
		System.out.println("TIME Team leader asks developer " + getTeamNumber() + getEmployNumber() + " a questions");
	
		// 10 minutes to answer a question
		try {
			sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Random randGen = new Random();
		// If false, team lead and the member go to ask the manager;
		// otherwise answer and naturally return to work.
		if (randGen.nextBoolean()) {
			myLead.askManagerQuestion();
		} else {
			System.out.println(theFirm.getClock().formatTime() + "Developer " + getTeamNumber() + getEmployNumber() + " answered their team lead's question.");
			goToWork();
		}
	}
	
	// meeting with other devs and team lead
	public void goToTeamMeeting(){
		theFirm.getConfRoom().enterRoom();
		theFirm.getConfRoom().leaveRoom();
	}

}
