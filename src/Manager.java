import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Manager extends Employee {

	private ArrayList<TeamLeader> myLeads = new ArrayList<TeamLeader>();
	private CyclicBarrier morningTeamLeadStandup = new CyclicBarrier(4);
	//private CyclicBarrier endOfDayMeeting = new CyclicBarrier(13);
	
	// Morning executive meeting, in minutes from midnight
	private int morningMeeting = 660;
	// Afternoon executive meeting, in minutes from midnight
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
	 * Answer a question from my team lead.
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

	/**
	 * Meeting with team leads.
	 */
	public void hostMorningStandup() {
		while (morningTeamLeadStandup.getNumberWaiting() < 3) {
			try {

				sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		logAction("starts morning leader meeting.");

		try {
			sleep(150);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		logAction("ended leader meeting.");
		try {
			// cyclic half-barrier
			morningTeamLeadStandup.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

	/**
	 * End of day meeting with all employees.
	 */
	public void hostEndOfDayMeeting() {
		while(theFirm.getConfRoom().getEndOfDayMeetingBarrier().getNumberWaiting() < 12) {
			try {
				sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		logAction("starts end of day status meeting.");

		try {
			sleep(150);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		logAction("ended status meeting.");

		try {
			// cyclic half-barrier
			theFirm.getConfRoom().getEndOfDayMeetingBarrier().await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

	public void run() {

		sleepUntil(480);
		logAction("arrives at work.");

		int timeToStartLunch = 720;
		hostMorningStandup();

		while (theFirm.getClock().getCurrTime() < morningMeeting) {
			if (hasQuestionForMe.size() != 0) {
				answerNoteToQuestion();
				logAction("answered team leader's question.");
			} else {
				try {
					sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		try {
			logAction("goes to morning executive meeting.");
			sleep(600);
			logAction("returns from morning executive meeting.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while (theFirm.getClock().getCurrTime() < timeToStartLunch) {
			if (hasQuestionForMe.size() != 0) {
				answerNoteToQuestion();
				logAction("answered team leader's question.");
			} else {
				try {
					sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		try {
			logAction("goes to lunch.");
			sleep(600);
			logAction("returns from lunch.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while (theFirm.getClock().getCurrTime() < afternoonMeeting) {
			if (hasQuestionForMe.size() != 0) {
				answerNoteToQuestion();
				logAction("answered team leader's question.");
			} else {
				try {
					sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		try {
			logAction("goes to afternoon executive meeting.");
			sleep(600);
			logAction("returns from afternoon executive meeting.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while (theFirm.getClock().getCurrTime() < 960) {
			if (hasQuestionForMe.size() != 0) {
				answerNoteToQuestion();
				logAction("answered team leader's question.");
			} else {
				try {
					sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		hostEndOfDayMeeting();

		while (theFirm.getGoneHome().getNumberWaiting() < 12) {
			if (hasQuestionForMe.size() != 0) {
				answerNoteToQuestion();
				logAction("answered team leader's question.");
			} else {
				try {
					sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		while (theFirm.getClock().getCurrTime() < 1020) {
			try {
				sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		goHome();

	}
}
