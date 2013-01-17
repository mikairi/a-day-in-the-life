import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Manager extends Employee {

	private ArrayList<TeamLeader> myLeads = new ArrayList<TeamLeader>();
	private CyclicBarrier morningTeamLeadStandup = new CyclicBarrier(4);
	//private CyclicBarrier endOfDayMeeting = new CyclicBarrier(13);
	private boolean isBusy = true;
	private int morningMeeting = 660;
	private int afternoonMeeting = 840;
	
	public Manager(Firm firm) {
		super(firm);
		setName("Manager");
		hasQuestionForMe = new ArrayList<Employee>();
	}

	public synchronized void addTeamLeader(TeamLeader lead) {
		myLeads.add(lead);
	}

	public boolean getIsManagerBusy() {
		//return isBusy;
		return true;
	}

	public CyclicBarrier getMorningTeamLeadStandup() {
		return morningTeamLeadStandup;
	}

	/**
	 * Answer a question from your team lead
	 */
	public void answerTeamLeadQuestion() {
		// 10 minutes to answer a question
		try {
			sleep(100);
			logAction("answered Team Leader question.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// meeting with team leads
	public void hostMorningStandup() {
		while(morningTeamLeadStandup.getNumberWaiting() < 3) {
			try {
				
				sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		logAction("started morning leader meeting");
		
		try {
			sleep(150);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		logAction("ended leader meeting");
		try {
			// cyclic half-barrier
			morningTeamLeadStandup.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void hostEndOfDayMeeting() {
		while(theFirm.getConfRoom().getendOfDayMeetingBarrier().getNumberWaiting() < 12) {
			try {
				sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		logAction("started end of day status meeting");
		
		try {
			sleep(150);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		logAction("status meeting ended");
		
		try {
			// cyclic half-barrier
			theFirm.getConfRoom().getendOfDayMeetingBarrier().await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run(){
		
		sleepUntil(480);		
		logAction("arrived at work");
		
		int timeToStartLunch = 720;
		int timeToEndLunch = 780;
		isBusy = false;
		
		hostMorningStandup();
		
		while(theFirm.getClock().getCurrTime() < morningMeeting) {
			if(hasQuestionForMe.size() != 0) {
				answerNoteToQuestion();				
				logAction("answered team leader's question");
			}
			else {
				try {
					sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		// Go to morning executive meeting
		isBusy = true;
		try {
			sleep(600);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		isBusy = false;
		
		while(theFirm.getClock().getCurrTime() < timeToStartLunch) {
			if(hasQuestionForMe.size() != 0) {
				answerNoteToQuestion();				
				logAction("answered team leader's question");
			}
			else {
				try {
					sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		// Go to lunch
		isBusy = true;
		try {
			sleep(600);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		isBusy = false;
		
		while(theFirm.getClock().getCurrTime() < afternoonMeeting) {
			if(hasQuestionForMe.size() != 0) {
				answerNoteToQuestion();				
				logAction("answered team leader's question");
			}
			else {
				try {
					sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		// Go to afternoon executive meeting
		isBusy = true;
		try {
			sleep(600);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		isBusy = false;
		
		while(theFirm.getClock().getCurrTime() < 960) {
			if(hasQuestionForMe.size() != 0) {
				answerNoteToQuestion();				
				logAction("answered team leader's question");
			}
			else {
				try {
					sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		hostEndOfDayMeeting();
		while(theFirm.getGoneHome().getNumberWaiting() < 12  ) {
			if(hasQuestionForMe.size() != 0) {
				answerNoteToQuestion();				
				logAction("answered team leader's question");
			}
			else {
				try {
					sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		while(theFirm.getClock().getCurrTime() < 1020){
			try {
				sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		goHome();
		
	}
}
