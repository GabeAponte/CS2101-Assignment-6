/**
 * A class that holds the properties 
 * and methods for the ElectionData object
 * 
 * @author Gabe Aponte
 */
import java.util.LinkedList;
import java.util.Scanner;
import java.util.HashMap;

class ElectionData {
	private LinkedList<String> ballot = new LinkedList<String>();
	private LinkedList<String> votes = new LinkedList<String>();
	private HashMap<String, LinkedList<Integer>> hash = new HashMap<String, LinkedList<Integer>>();

	ElectionData() {}

	/**
	 * This is the getBallot method which produces the
	 * the value in the ballot field
	 * 
	 * @return ballot  a linkedlist of strings representing the candidates on the ballot
	 */
	public LinkedList<String> getBallot(){
		return this.ballot;
	}
	
	/**
	 * This is the getVotes method which produces the
	 * the value in the votes field
	 * 
	 * @return votes  a linkedlist of strings representing the candidates voted for
	 */
	public LinkedList<String> getVotes(){
		return this.votes;
	}

	/**
	 * This is the processVote method which consumes three candidate names and process 
	 * them for the voting process
	 * 
	 * @param first  a string representing the name of the first candidate inputed
	 * @param second  a string representing the name of the second candidate inputed
	 * @param third  a string representing the name of the third candidate inputed
	 * @throws DuplicateVotesException  an exception thrown when a candidate is voted for more than once 
	 * @throws UnknownCandidateException   an exception thrown when a candidate is voted for but is not on the ballot
	 */
	public void processVote(String first, String second, String third) throws DuplicateVotesException, UnknownCandidateException{
		if(!hash.containsKey(first)) {
			throw new UnknownCandidateException(first);
		}
		
		if(!hash.containsKey(second)) {
			throw new UnknownCandidateException(second);
		}

		if(!hash.containsKey(third)) {
			throw new UnknownCandidateException(third);
		}
		
		if(first.equals(second) || first.equals(third)){
			throw new DuplicateVotesException(first);
		}

		if(second.equals(first) || second.equals(third)){
			throw new DuplicateVotesException(second);
		}

		if(third.equals(first) || third.equals(second)){
			throw new DuplicateVotesException(third);
		}

		hash.get(first).set(0, hash.get(first).get(0) + 1);
		hash.get(second).set(1, hash.get(second).get(1) + 1);
		hash.get(third).set(2, hash.get(third).get(2) + 1);
	}

	/**
	 * This is the addCandidate method which takes in a candidate and adds
	 * it to the hashmap and the ballot
	 * 
	 * @param canidate  a string representing the name of the candidate to be added
	 * @throws CandidateExistsException  an exception thrown when the candidate to be added already exists
	 */
	public void addCandidate(String canidate) throws CandidateExistsException{
		LinkedList <Integer> order = new LinkedList<Integer>();

		if(hash.containsKey(canidate)){
			throw new CandidateExistsException(canidate);
		} else {
			order.add(0);
			order.add(0);
			order.add(0);
			
			hash.put(canidate, order);
			ballot.add(canidate);
		}
	} 

	/**
	 * This is the findWinnerMostFirstVotes method which produces the winner when a candidate 
	 *  has more than 50% of first place votes or produces the string "Runoff required" if no 
	 *  candidate receives more than 50% of the votes, 
	 *  
	 * @return candidate or Runoff required  a string representing the name of the candidate or the string "Runoff required"
	 */
	public String findWinnerMostFirstVotes(){
		float mostVotes = 0;

		for(String candidate : ballot) {
			mostVotes = hash.get(candidate).get(0) + mostVotes;
		}
		if(mostVotes > 0) {
			for(String candidate : ballot) {
				float percent = hash.get(candidate).get(0)/mostVotes;

				if((percent * 100) > 50){         
					return candidate;
				}
			}
		}
		return "Runoff required";
	}

	/**
	 * This is the findWinnerMostPoints method which produces the winner the winner is the 
	 * candidate with the most points under the following formula: three points for each 
	 * first-place vote they received, two points for each second-place vote they received, 
	 * and one point for each third-place vote they received.
	 * 
	 * @return winner  a local variable containing a string representing the name of the winning candidate
	 */
	public String findWinnerMostPoints() {
		int mostPoints = 0;
		String winner = null;

		for(String candidate : ballot) {
			int candidatePoints = ((hash.get(candidate).get(0) * 3) + (hash.get(candidate).get(1) * 2) + hash.get(candidate).get(2));

			if(candidatePoints > mostPoints) {
				mostPoints = candidatePoints;
				winner = candidate;
			}
		}
		return winner;
	}
}
