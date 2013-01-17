import java.util.Timer;
import java.util.TimerTask;

/**
 * Keeps track of the current time at the company.
 * 
 * Special thanks to Kristen Mills for the inspiration and help with debugging.
 * 
 * @author Andrew Lyne
 * @collaborator Kristen Mills
 * 
 */
public class FirmClock {

	/**
	 * Start of the day ( 7:45am ) in minutes
	 */
	private static final int START_DAY = 465;

	/**
	 * End of the day ( 5:00pm )in minutes
	 */
	private static final int END_DAY = 1020;
	
	/**
	 * Time scale: 1 minute in real life is 10 ms in simulation.
	 */
	public static final int TIME_SCALE = 10;

	/**
	 * The clock
	 */
	private final Timer timer = new Timer();

	/**
	 * Time in minutes of the current time. Starts at START_DAY (7:45 am) and
	 * ends at END_DAY (5:15pm).
	 */
	private int currTime = START_DAY;

	/**
	 * Starts the timer at 8:00 which will increment the minute of the clock
	 * every 10ms until 17:00 (5:00pm)
	 */
	public void start() {
		TimerTask task = new TimerTask() {
			public void run() {
				if (currTime >= END_DAY + 15) {
					stop();
				}
//				System.out.println(formatTime());
				currTime += 1;
			}
		};
		timer.schedule(task, 0, TIME_SCALE);

	}

	/**
	 * Stops the timer
	 */
	public void stop() {
		timer.cancel();
	}

	/**
	 * Checks if the current time is past or equal to the end of the day
	 * 
	 * @return True if day is over, false otherwise
	 */
	public boolean isEndOfDay() {
		if (currTime >= END_DAY)
			return true;
		return false;
	}

	/**
	 * Get current time in minutes of the system.
	 * 
	 * @return the current time in minutes
	 */
	public long getCurrTime() {
		return currTime;
	}

	/**
	 * Converts the long of minutes into an hour minutes representation
	 * 
	 * @return a long array where the first value is hours and second value is
	 *         minutes
	 */
	private long[] hourMinutes() {
		long curcurrTime = currTime;
		long hour = (curcurrTime / 60) % 24;
		long minutes = curcurrTime % 60;
		long[] time = { hour, minutes };
		return time;
	}

	/**
	 * Formatted string of the current time
	 * 
	 * @return a string of the current time in the form HH:MM
	 */
	public String formatTime() {
		long[] time = hourMinutes();
		if (time[0] == 0) {
			time[0] = 12;
		}

		return String.format("%d:%02d", time[0], time[1]);
	}

}
