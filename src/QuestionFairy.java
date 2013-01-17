import java.util.ArrayList;
import java.util.Random;



public class QuestionFairy extends Thread{
	
	private ArrayList<Employee> workers;
	int maxTime;
	int minTime;
	Random r;
	Firm myFirm;
	
	/**
	 * Throughout the work day this critter will randomly select a Developer 
	 * or Team Leader and inspire them with great knowledge of all thing 
	 * concurrent. Confused by this presence, these people will then ask 
	 * questions of their peers to make sence of this blessing. Since this is 
	 * a very demanding job, the fairy sleeps in until 9am and goes to sleep 
	 * at 4pm. 
	 * 
	 * @param people - collection of Employee threads to "Gift" 
	 * 		questions to
	 * @param maxTimeToWait - Max Time in milliseconds to wait 
	 * 		between questions
	 * @param minTimeToWait - Min Time in milliseconds to wait 
	 * 		between questions
	 */
	public QuestionFairy(ArrayList<Employee> people, int minTimeToWait, int maxTimeToWait, Firm theFirm){
		maxTime = maxTimeToWait;
		minTime = minTimeToWait;
		workers = people;
		r = new Random();
		myFirm = theFirm;
	}
	
	/**
	*	Causes the thread to wait until the FirmClock associated
	*   with this object reads the passed time. Time expressed in
	*   minutes.
	*
	*	@param time    Time to sleep until
	*/
	void sleepUntil(long time){
		while( myFirm.getClock().getCurrTime() < time ){
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void run(){
		
		/* Waiting for the main to wake up the fairy at 9am. He is a heavy sleeper 
		* so he is the last to arrive at the office.
		*/
		System.out.println( "Question Fairy Thread Starts.");
		sleepUntil(540);
		System.out.println( "Question Fairy awakens from his slumber.");
		// Day Begins	
		while( myFirm.getClock().getCurrTime() < 960 ){
			int sleepTime = r.nextInt(maxTime - minTime) + minTime;
			int personToInspire = r.nextInt( workers.size());
			workers.get(personToInspire).inspire();
			try{
				sleep( sleepTime);
			}
			catch( InterruptedException e){
				System.err.println( e.getMessage() );
			}
		}
		System.out.println( "Question Fairy Goes To Sleep.");
		// End of Thread
	}
	
	

}
