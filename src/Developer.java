import java.util.*;
import java.util.concurrent.BrokenBarrierException;

public class Developer extends Employee {

	/*
	*	Variables
	*/
	
	// This developer's team leader
	private  TeamLeader myLead;
	
	public Developer(int teamNumber, int employNumber, TeamLeader lead, Firm firm) {
		super(firm);
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
		logAction("asks a question.");
		
		myLead.leaveNote(this);
	}
	
	/**
	*	Answer a question from your team lead
	*/
	public void answerTeamLeadersQuestion(){

		logAction("answers his team lead's question");
		// 10 minutes to answer a question
		try {
			sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		myLead.gotQuestionAnswered();
		hasQuestionForMe = null;
	}
	
	// meeting with other devs and team lead
	public void goToTeamMeeting(){
		
		logAction("goes to team meeting.");
		
		try {
			myLead.getSmallTeamConference().await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
		
		logAction("returns from team meeting.");
	}

	public void run(){
		sleepUntil( 480);
		goToWork();
		
		int timeToStartLunch = randomNum.nextInt(120) + 660;
		
		goToTeamMeeting();
		
		// The time leading up to lunch time
		while(theFirm.getClock().getCurrTime() < timeToStartLunch) {
			// Needed to implement this note logic to avoid deadlock (see documentation)
			if(hasQuestionForMe != null) {
				answerNoteToQuestion();
			}
			else if(hasQuestion) {
				//System.out.println("IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
				askTeamLeadQuestion();
				while(hasQuestion) {
					try {
						sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				//System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
			}
			else {
				try {
					sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		goToLunch();
		
		// The time after lunch has finished
		while(theFirm.getClock().getCurrTime() < 960) {
			if(hasQuestionForMe != null) {
				answerNoteToQuestion();
			}
			else if(hasQuestion) {
				while(hasQuestion) {
					try {
						sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			else {
				try {
					sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		goToEndOfDayMeeting();
		
		// The time after the final meeting until the end of day
		while(theFirm.getClock().getCurrTime() < endTime || hasQuestionForMe != null) {
			if(hasQuestionForMe != null) {
				answerNoteToQuestion();
			}
			else if(hasQuestion) {
				if(myLead.areYouHere())	{
					while(hasQuestion) {
						try {
							sleep(5);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				else {
//					logAction("team leader went home, I'll ask question tomorrow");
					hasQuestion = false;
				}
			}
			else {
				try {
					sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		goHome();
		
		
	}
}
