import java.util.Random;

public abstract class Employee extends Thread {

	final private int NUMBER_MINUTES_TO_WORK = 480;
	
	protected int employNumber;
	protected int teamNumber;
	
	protected Developer hasQuestionForMe = null;
	
	protected Firm theFirm;
	
	private boolean idle = false;
	private boolean eatenLunch = false;
	private boolean hasGoneHome = false;

	protected Random randomNum = new Random();
	
	private int arrivalTime;	
	private int lunchTime;
	protected int endTime;
	private long simulationTime;
	protected boolean hasNote = false;
	protected boolean hasQuestion = false;
	
	public Employee(Firm firm) {
		super();
		this.theFirm = firm;
	}

	/**
	 * Sets a number to reference the individual employee
	 * @param employNumber
	 */
	public void setEmployeeNumber(int employNumber) {
		this.employNumber = employNumber;
	}
	
	/**
	 * Gets a specified number assigned to each employee
	 * @return employee reference number
	 */
	public int getEmployNumber() {
		return employNumber;
	}
	
	/**
	 * Sets the team the employee is assigned to
	 * @param teamNumber
	 */
	public void setTeamNumber(int teamNumber) {
		this.teamNumber = teamNumber;
	}
	
	/**
	 * Gets a specified team number assigned to each employee
	 * @return employee team number
	 */
	public int getTeamNumber() {
		return teamNumber;
	}
	
	public boolean areYouHere(){
		return !hasGoneHome;
	}
	
	public void leaveNote(Developer dev) {
		hasQuestionForMe = dev;
	}
	
	public void answerNoteToQuestion() {
		
		try {			
			sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		hasQuestionForMe.gotQuestionAnswered();		
		hasQuestionForMe = null;
		
		logAction("answered question");
	}
	
	public void gotQuestionAnswered() {
		hasQuestion = false;
	}


	/**
	*	Causes the thread to wait until the FirmClock associated
	*   with this object reads the passed time. Time expressed in
	*   minutes.
	*
	*	@param time    Time to sleep until
	*/
	

	void sleepUntil(long time){
		while( theFirm.getClock().getCurrTime() < time ){
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Specifies an employee arriving to the work place
	 */
	protected void goToWork() {

		idle = false;

		// generate a time between 0 and 30 minutes
		arrivalTime = randomNum.nextInt(30);
		
		// Pre-determine the employee's time taken for lunch [FATE]
		// generate a time between 0 and 30 - time arrived to work
		lunchTime = randomNum.nextInt(30-arrivalTime) + 30;
		
		// calculate end time for employee to leave
		endTime = arrivalTime + lunchTime + NUMBER_MINUTES_TO_WORK;
		
		// convert arrival time to simulation time
		simulationTime = arrivalTime * 10;		

		try {
			sleep(simulationTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		logAction("arrived to work.");
		
		returnToWork();
	}

	/**
	 * Specifies an employee taking their lunch break
	 */
	protected void goToLunch() {

		idle = false;
		
		logAction("went to lunch.");
		
		// convert lunch time to simulation time
		simulationTime = lunchTime * 10;	

		try {
			sleep(simulationTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		eatenLunch = true;
		returnToWork();
	}
	
	protected void goToEndOfDayMeeting() {
		logAction("went to project status update meeting");
		theFirm.getConfRoom().attendEndOfDayMeeting();
		logAction("meeting concluded");
		returnToWork();
	}

	/**
	 * Specifies an employee leaving the work place
	 */
	protected void goHome() {
		logAction("has left the workplace.");
		theFirm.leaveFirm();
	}

	/**
	 * Set the state to an "idle" working state
	 */
	protected void returnToWork() {
		logAction("is now working.");
		idle = true;
	}

	/**
	 * Log the action of the employee to standard output.
	 * @param verb the action the employee took, usually a verb phrase.
	 */
	protected void logAction(String verb) {
		System.out.println(theFirm.getClock().formatTime() + " " + getName() + " " + verb);
	}

	public void inspire() {
		System.out.println("I AM INSPIRED WITH ALL THINGS CONCURRENT");
		hasQuestion = true;
	}
}
