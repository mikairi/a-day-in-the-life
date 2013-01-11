import java.util.Random;


public abstract class Employee extends Thread {

	private int employNumber;
	private int teamNumber;
	
	private boolean idle;
	private boolean eatenLunch;

	private Random randomNum = new Random();
	
	private int arrivalTime;
	private int lunchTime;
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
	private void goToWork() {

		idle = false;

		// generate a time between 0 and 30 minutes
		arrivalTime = randomNum.nextInt(30);
		
		// convert arrival time to simulation time
		simulationTime = arrivalTime * 10;		

		try {
			sleep(simulationTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Employee " + employNumber + " on team " + getTeamNumber() + " arrived to work.");

		returnToWork();
	}

	/**
	 * Specifies an employee leaving the work place
	 */
	private void goHome() {
		System.out.println("Employee " + employNumber + " on team " + getTeamNumber() + " has left the work place.");		
	}

	/**
	 * Specifies an employee taking their lunch break
	 */
	private void goToLunch() {

		idle = false;
		eatenLunch = true;

		// generate a time between 0 and 60 - time arrived to work
		lunchTime = randomNum.nextInt(60-arrivalTime);
		
		// convert lunch time to simulation time
		simulationTime = lunchTime * 10;	

		try {
			sleep(simulationTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Employee " + employNumber + " on team " + getTeamNumber() + " went to lunch.");

		returnToWork();
	}

	/**
	 * Set the state to an "idle" working state
	 */
	private void returnToWork() {		
		System.out.println("Employee " + employNumber + " on team " + getTeamNumber() + " is now working.");		
		idle = true;
	}
}
