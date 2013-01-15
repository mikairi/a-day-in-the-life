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
	
	
	}

	// meeting with team leads
	public void startStandUp(){
	
	}
	
}

