import java.util.*;

public class Manager extends Employee {

	private ArrayList<TeamLeader> myLeads;
	
	/**
	*	Answer a question from your team lead
	*/
	public void answerTeamLeadQuestion(){
		System.out.println("TIME Team leader asks Manager a question.");
		
		// 10 minutes to answer a question
		try {
			sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		

		System.out.println("TIME Manager provides an answer to team leader's question.");	
	}

	// meeting with team leads
	public void startStandUp(){
		
		// Manager begins morning stand-up meeting
		System.out.println("TIME Manager and team leaders are engaged in this morning's stand-up meeting.");
		
		// 15 minutes for stand-up meeting
		try {
			sleep(150);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	void goToWork() {
		System.out.println("Manager arrived to work.");
		super.goToWork();
	}
	
	void goHome() {
		System.out.println("Manager has left the work place.");
		super.goHome();
	}
	
	void goToLunch() {
		System.out.println("Manager went to lunch.");
		super.goToLunch();
	}
	
	void returnToWork() {
		System.out.println("Manager is now working.");
		super.returnToWork();
	}
}

