import java.util.*;

public class Manager extends Employee {

	private ArrayList<TeamLeader> myLeads = new ArrayList<TeamLeader>();
	
	public Manager() {
		super();
		setName("Manager");
	}
	
	public synchronized void addTeamLeader(TeamLeader lead) {
		myLeads.add(lead);
	}
	
	/**
	*	Answer a question from your team lead
	*/
	public void answerTeamLeadQuestion(){
	// 10 minutes to answer a question
			try {
				sleep(100);
				logAction("answered Team Leader question.");
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
}

