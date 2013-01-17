import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TeamLeader extends Employee {

	/*
	 * Variables
	 */

	// Group of employees on this Team Leader's team
	private ArrayList<Developer> myTeam = new ArrayList<Developer>();

	// Not including team leader, a team has 3 developers
	private CyclicBarrier smallTeamConference = new CyclicBarrier(4);

	// This team leader's manager
	private Manager myManager;

	public TeamLeader(int teamNumber, Manager manager, Firm firm) {
		super(firm);
		this.teamNumber = teamNumber;
		this.employNumber = 1;
		this.myManager = manager;

		// Add self to manager's list of team leaders
		myManager.addTeamLeader(this);
		setName("Developer " + getTeamNumber() + getEmployNumber());
	}

	public CyclicBarrier getSmallTeamConference() {
		return smallTeamConference;
	}

	public synchronized void addTeamMember(Developer member) {
		myTeam.add(member);
	}

	/**
	 * Ask a question to your manager
	 */
	public void askManagerQuestion() {
		hasQuestion = true;
		logAction("asked manager a question");
		myManager.leaveNote(this);
	}

	/**
	 * Answer a question from your team
	 */
	public void answerTeamQuestion() {
		Random randGen = new Random();
		// If true, team lead and the member go to ask the manager.
		// Otherwise automatically answer and naturally return to work.
		// TODO: The team member also needs to go to the manager.
		if (randGen.nextBoolean()) {
			logAction("asks manager for help with Developer question.");
			askManagerQuestion();
		}

		else {
			logAction("answered developer's question.");
		}
	}

	// meeting with team members
	public void startTeamMeeting() {
		while (smallTeamConference.getNumberWaiting() < 3) {
			try {
				sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		while (!theFirm.getConfRoom().canEnter()) {
			try {
				sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		logAction("starts team meeting.");

		try {
			sleep(150);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		logAction("ends team meeting.");

		theFirm.getConfRoom().leaveRoom();

		try {
			// cyclic half-barrier
			smallTeamConference.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

	public void attendStandup() {
		logAction("goes to leader standup meeting");

		try {
			myManager.getMorningTeamLeadStandup().await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logAction("returns from leader standup meeting");
	}
	
	public void run() {
		sleepUntil(480);
		goToWork();

		int timeToStartLunch = randomNum.nextInt(120) + 660;

		// Go to standup meeting with the other team leads and manager
		attendStandup();

		// Begin their routine team meeting
		startTeamMeeting();

		// The time leading up to lunch time
		// To avoid deadlock, Team leaders will always answer questions before they try to 
		// ask a question. Setting the priorities in this way, the deadlock where all
		// employees are waiting for questions to be answered is avoided. 
		while (theFirm.getClock().getCurrTime() < timeToStartLunch) {

			if (hasQuestionForMe != null) {
				if (randomNum.nextBoolean()) {
					answerNoteToQuestion();
				} else {
					if (myManager.getIsManagerBusy()) {
						logAction("manager is busy, team leader made up an answer");
						answerNoteToQuestion();
					} else {
						askManagerQuestion();
						while (hasQuestion) {
							try {
								sleep(5);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						answerNoteToQuestion();
					}
				}

			} else if (hasQuestion) {
				int personToAsk = randomNum.nextInt(myTeam.size());
				myTeam.get(personToAsk).leaveNote(this);
				while (hasQuestion) {
					try {
						sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} else {
				try {
					sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		goToLunch();

		// The time after lunch has finished
		while (theFirm.getClock().getCurrTime() < 960) {
			if (hasQuestionForMe != null) {
				if (randomNum.nextBoolean()) {
					answerNoteToQuestion();
				} else {
					if (myManager.getIsManagerBusy()) {
						logAction("manager is busy, team leader made up an answer");
						answerNoteToQuestion();
					} else {
						askManagerQuestion();
						while (hasQuestion) {
							try {
								sleep(5);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						answerNoteToQuestion();
					}
				}

			} else if (hasQuestion) {
				int personToAsk = randomNum.nextInt(myTeam.size());
				myTeam.get(personToAsk).leaveNote(this);
				while (hasQuestion) {
					try {
						sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} else {
				try {
					sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		goToEndOfDayMeeting();

		// The time after the final meeting until the end of day
		while (theFirm.getClock().getCurrTime() < endTime
				|| hasQuestionForMe != null) {
			if (hasQuestionForMe != null) {
				if (randomNum.nextBoolean()) {
					answerNoteToQuestion();
				} else {
					if (myManager.getIsManagerBusy()) {
						logAction("manager is busy, team leader made up an answer");
						answerNoteToQuestion();
					} else {
						askManagerQuestion();
						while (hasQuestion) {
							try {
								sleep(5);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						answerNoteToQuestion();
					}
				}
			}

			else if (hasQuestion) {
				int personToAsk = randomNum.nextInt(myTeam.size());
				if (myTeam.get(personToAsk).areYouHere()) {
					myTeam.get(personToAsk).leaveNote(this);
				} else {
					logAction("developer went home, I'll ask question tomorrow");
					hasQuestion = false;
				}
				while (hasQuestion) {
					try {
						sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} else {
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
