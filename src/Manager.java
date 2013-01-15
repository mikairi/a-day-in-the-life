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
				System.out.println( Thread.getName() + "answers TeamLead question.");
			}	 
			catch (InterruptedException e) {
				e.printStackTrace();
			}	
	
	}

	// meeting with team leads
	public void startStandUp(){
	
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

