public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Firm firm = new Firm();
		Manager manager = new Manager(firm);
		for (int team = 1; team < 4; team++) {
			TeamLeader lead = new TeamLeader(team, manager, firm);
			lead.start();
			for (int employ = 2; employ < 5; employ++) {
				Developer dev = new Developer(team, employ, lead, firm);
				dev.start();
			}
		}
		firm.startDay();
	}

}
