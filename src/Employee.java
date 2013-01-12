import java.util.Random;


public abstract class Employee extends Thread {

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
		
		System.out.println("Employee " + getTeamNumber() + getTeamNumber() + " arrived to work.");

		returnToWork();
	}

	/**
	 * Specifies an employee leaving the work place
	 */
	void goHome() {
		System.out.println("Employee " + getTeamNumber() + getEmployNumber() + " has left the work place.");
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

		System.out.println("Employee " + getTeamNumber() + getEmployNumber() + " went to lunch.");
		
		returnToWork();
	}

	/**
	 * Set the state to an "idle" working state
	 */
	void returnToWork() {		
		System.out.println("Employee " + getTeamNumber() + getEmployNumber() + " is now working.");
		idle = true;
	}
}
