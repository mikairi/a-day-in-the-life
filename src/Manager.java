import java.util.*;

public class Manager extends Employee {

	private ArrayList<TeamLeader> myLeads;
	
	/**
	*	Answer a question from your team lead
	*/
	public void answerTeamLeadQuestion(){

		// 10 minutes to answer a question
		try {
			sleep(100);
			System.out.println("TIME " + Thread.currentThread().getName() + "answers TeamLead question.");
		}	 
		catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}

	// meeting with team leads
	public void startStandUp(){

		// 15 minutes for stand-up meeting 
		try {
			System.out.println("TIME " + Thread.currentThread().getName() + " engages in morning stand-up meeting with TeamLeads.");
			sleep(150);
			System.out.println("TIME " + Thread.currentThread().getName() + " concludes the morning stand-up meeting with TeamLeads.");
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	void goToWork() {
		System.out.println("TIME Manager arrived to work.");
		super.goToWork();
	}
	
	void goHome() {
		System.out.println(" TIME Manager has left the work place.");
		super.goHome();
	}
	
	void goToLunch() {
		System.out.println("TIME Manager went to lunch.");
		super.goToLunch();
	}
	
	void returnToWork() {
		System.out.println("TIME Manager is now working.");
		super.returnToWork();
	}
}

