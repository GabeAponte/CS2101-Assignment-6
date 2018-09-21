/**
 * This is the main class that runs the Voting Machine
 * user interface with a set of candidates for testing purposes  
 * 
 * @author Gabe Aponte
 */
public class Main {
	public static void main(String[] args) {

		VotingMachine test = new VotingMachine();
		try {
			test.data.addCandidate("Joe");
			test.data.addCandidate("Smith");
			test.data.addCandidate("Gabe");
			test.data.addCandidate("Hunter");
			test.data.addCandidate("Sam");
		}
		catch(Exception e) {
			System.out.println("Error creating election with test data: "+e);
		}
		test.screen();
	}
}