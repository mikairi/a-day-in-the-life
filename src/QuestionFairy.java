import java.util.ArrayList;
import java.util.Random;



public class QuestionFairy extends Thread{
	
	private ArrayList<Employee> workers;
	int maxTime;
	int minTime;
	Random r;
	boolean awake;
	
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
	public QuestionFairy(ArrayList<Employee> people, int maxTimeToWait, int minTimeToWait){
		maxTime = maxTimeToWait;
		maxTime = maxTimeToWait;
		workers = people;
		r = new Random();
		awake= true;
	}
	
	
	public void run(){
		
		/* Waiting for the main to wake up the fairy at 9am. He is a heavy sleeper 
		* so he is the last to arrive at the office.
		*/
		System.out.println( "Question Fairy Thread Starts.");
		try{
			wait();
		}
		catch( Exception e ){
			System.err.println( e.getMessage() );
				
		}
		// Day Begins	
		while( awake){
			System.out.println( "Question Fairy wakes up.");
			int sleepTime = r.nextInt(maxTime-minTime) + minTime;
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
