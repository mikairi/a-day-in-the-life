import java.util.ArrayList;
import java.util.Random;

public abstract class Employee extends Thread {

	final private int NUMBER_MINUTES_TO_WORK = 480;
	
	protected int employNumber;
	protected int teamNumber;
	
	protected ArrayList<Employee> hasQuestionForMe = null;
	
	protected Firm theFirm;
	
	private boolean idle = false;
	private boolean eatenLunch = false;
	private boolean hasGoneHome = false;

	protected Random randomNum = new Random();
	
	private int arrivalTime;	
	private int lunchTime;
	protected int endTime;
	private long simulationTime;
	protected boolean hasQuestion = false;
	
	public Employee(Firm firm) {
		super();
		this.theFirm = firm;
	}
	
	/**
	 * Gets a specified number assigned to each employee
	 * @return employee reference number
	 */
	public int getEmployNumber() {
		return employNumber;
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
	
	public void leaveNote(Employee emp) {
		hasQuestionForMe.add(emp);
	}
	
	public void answerNoteToQuestion() {
		
		try {			
			sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		hasQuestionForMe.get(0).gotQuestionAnswered();	
		hasQuestionForMe.remove(0);
		
		logAction("answered question");
	}
	
	public void gotQuestionAnswered() {
		hasQuestion = false;
		logAction("got question answered");
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

		logAction("arrives at work.");
		
		returnToWork();
	}

	/**
	 * Specifies an employee taking their lunch break
	 */
	protected void goToLunch() {

		idle = false;
		
		logAction("goes to lunch.");
		
		// convert lunch time to simulation time
		simulationTime = lunchTime * 10;	

		try {
			sleep(simulationTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		eatenLunch = true;
		logAction("returns from lunch.");
		returnToWork();
	}
	
	protected void goToEndOfDayMeeting() {
		logAction("goes to project status update meeting.");
		theFirm.getConfRoom().attendEndOfDayMeeting();
		logAction("returns from project status meeting.");
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
		logAction("I AM INSPIRED WITH ALL THINGS CONCURRENT");
		hasQuestion = true;
	}
}
