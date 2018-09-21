/**
 * A class that holds the properties 
 * and methods for the VotingMachine object
 * 
 * @author Gabe Aponte
 */
import java.util.Scanner;

public class VotingMachine {
	ElectionData data = new ElectionData ();
	Scanner keyboard = new Scanner(System.in);
	
	/**
	 * This is the printBallot method which prints out the names of the 
	 * candidates on the ballot
	 */
	public void printBallot() {
		System.out.println("The candidates are ");
		for (String s : data.getBallot()) {
			System.out.println(s);
		}
	}
	
	/**
	 * This is the addWriteIn method which adds a name to the ballot when the user
	 * wants to add that name in
	 * 
	 * @param name  a string representing the name of the candidate to be added in
	 */
	public void addWriteIn(String name) {
		try {
			data.addCandidate(name);
			System.out.println("Candidate added successfully.");
		} catch (CandidateExistsException e) {
			System.out.println("This candidate already exists.");
		}
	}
	
	/**
	 * This is the screen method which prints out the user interface that a voter
	 * will use to vote
	 */
	public void screen() {
		this.printBallot();
		System.out.println("Who is the first candidate you want to vote for?");
		String candidate = keyboard.next();
		System.out.println("Who is the second candidate you want to vote for?");
		String candidate2 = keyboard.next();
		System.out.println("Who is the third candidate you want to vote for?");
		String candidate3 = keyboard.next();
		try {
			data.processVote(candidate, candidate2, candidate3);
		} catch (DuplicateVotesException e) {
			System.out.println("You can't vote for the candidate "+e.getDuplicateName()+" more than once.");
			screen();
		} catch (UnknownCandidateException e) {
			System.out.println("Candidate "+e.getDNEName()+" not found. Do you want to add them?");
			String response = keyboard.next();
			if(response.equalsIgnoreCase("y")) {
				addWriteIn(e.getDNEName());
			}
			screen();
		}
	}
}
