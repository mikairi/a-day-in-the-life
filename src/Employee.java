import java.util.Random;


public abstract class Employee extends Thread {

	private static FirmClock clock;

	final private int NUMBER_HOURS_TO_WORK = 480;
	
	private int employNumber;
	private int teamNumber;
	
	ConferenceRoom confRoom;
	ManagersOffice manOffice;
	
	private boolean idle;
	private boolean eatenLunch;

	private Random randomNum = new Random();
	
	private int arrivalTime;	
	private int lunchTime;
	private int endTime;
	private long simulationTime;
	
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


	/**
	*	Causes the thread to wait until the FirmClock associated
	*   with this object reads the passed time. Time expressed in
	*   minutes.
	*
	*	@param time    Time to sleep until
	*/
	void sleepUntil(int time){
		while( clock.getCurrTime() < time ){
			Thread.sleep(5);
		}
	}


	/**
	 * Specifies an employee arriving to the work place
	 */
	void goToWork() {

		idle = false;

		// generate a time between 0 and 30 minutes
		arrivalTime = randomNum.nextInt(30);
		
		// Pre-determine the employee's time taken for lunch [FATE]
		// generate a time between 0 and 30 - time arrived to work
		lunchTime = randomNum.nextInt(30-arrivalTime) + 30;
		
		// calculate end time for employee to leave
		endTime = arrivalTime + lunchTime + NUMBER_HOURS_TO_WORK;
		
		// convert arrival time to simulation time
		simulationTime = arrivalTime * 10;		

		try {
			sleep(simulationTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Thread.sleep( arrivalTime * 10);
		returnToWork();
	}

	/**
	 * Specifies an employee leaving the work place
	 */
	void goHome() {
	}

	/**
	 * Specifies an employee taking their lunch break
	 */
	void goToLunch() {

		idle = false;
		eatenLunch = true;
		
		// convert lunch time to simulation time
		simulationTime = lunchTime * 10;	

		try {
			sleep(simulationTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		returnToWork();
	}

	/**
	 * Set the state to an "idle" working state
	 */
	void returnToWork() {
		idle = true;
	}

	public void inspire() {
		System.out.println("I AM INSPIRED WITH ALL THINGS CONCURRENT");
		
	}
}
