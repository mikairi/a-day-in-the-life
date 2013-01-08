
public abstract class Employee extends Thread {

	private boolean idle;
	private boolean eatenLunch;


	private void goToWork();
	private void goHome();
	private void goToLunch();
	private void returnFromLunch();
	private void returnFromBusy();
}

