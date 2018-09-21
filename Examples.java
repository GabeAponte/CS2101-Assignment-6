/**
 * this the the Examples class that
 * holds all the test cases for the program
 * 
 * @author Gabe Aponte
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import org.junit.*;

public class Examples {

	public ElectionData ElectionOne(){
		ElectionData election = new ElectionData();
		try {
			election.addCandidate("Joe");
			election.addCandidate("Smith");
			election.addCandidate("Gabe");
			election.processVote("Joe", "Smith", "Gabe");
			election.processVote("Joe", "Gabe", "Smith");
			election.processVote("Smith", "Joe", "Gabe");
		}
		catch(Exception e) {
			System.out.println("Election 1 Error: "+e);
		}
		return election;
	}

	public ElectionData ElectionTwo(){
		ElectionData election = new ElectionData();
		try {
			election.addCandidate("Emily");
			election.addCandidate("Ed");
			election.addCandidate("Hunter");		
			election.addCandidate("Louis");
			election.processVote("Emily", "Ed", "Louis");
			election.processVote("Ed", "Hunter", "Louis");
			election.processVote("Louis", "Hunter", "Ed");
			election.processVote("Hunter", "Louis", "Emily");
		}
		catch(Exception e) {
			System.out.println("Election 2 Error: " +e);
		}
		return election;
	}

	public ElectionData ElectionThree(){
		ElectionData election = new ElectionData();
		try {
			election.addCandidate("Root");		
			election.processVote("Root", "Root", "Root");
		} catch (Exception e) {
			System.out.println("Election 3 Error: " +e);
		}
		return election;
	}	

	public ElectionData ElectionFour(){
		ElectionData election = new ElectionData();
		try {
			election.addCandidate("Superman");
			election.addCandidate("Batman");
			election.addCandidate("Spiderman");
			election.processVote("Superman", "Batman", "Spiderman");
		} catch (Exception e) {
			System.out.println("Election 4 Error: "+e);
		}
		return election;
	}	

	public ElectionData ElectionFive(){
		ElectionData election = new ElectionData();
		try {
			election.addCandidate("Sam");
			election.addCandidate("Lucas");
			election.addCandidate("Derek");
			election.addCandidate("Lydia");
			election.processVote("Lydia", "Sam", "Lucas");
			election.processVote("Lucas", "Derek", "Sam");
			election.processVote("Lydia", "Sam", "Derek");
		} catch (Exception e) {
			System.out.println("Election 5 Erorr: "+e);
		}
		return election;
	}
	
	public ElectionData ElectionSix(){
		ElectionData election = new ElectionData();
		try {
			election.addCandidate("Trump");
			election.addCandidate("Vlad");
			election.addCandidate("Obama");
			election.addCandidate("Tomatohead");
			election.addCandidate("Lucas");
			election.addCandidate("Derek");
			election.processVote("Tomatohead", "Obama", "Trump");
			election.processVote("Vlad", "Lucas", "Derek");
		} catch (Exception e) {
			System.out.println("Election 6 Erorr: "+e);
		}
		return election;
	}

	//tests processVote to see if the unknown candidate exception works
	@Test (expected = UnknownCandidateException.class)
	public void testThrowsUnknown () throws DuplicateVotesException, UnknownCandidateException {
		ElectionFive().processVote("Sam", "Lydia", "hello");
	}

	//tests processVote to see that the unknown candidate exception catches first
	@Test (expected = UnknownCandidateException.class)
	public void testUnknownBeforeDuplicate () throws DuplicateVotesException, UnknownCandidateException {
		ElectionFive().processVote("Sam", "RIP HARAMBEE", "Sam");
	}

	//tests processVote to see if the duplicate vote exception works
	@Test (expected = DuplicateVotesException.class)
	public void testDuplicateVote() throws DuplicateVotesException, UnknownCandidateException {
		ElectionFive().processVote("Sam", "Lydia", "Lydia");
	}

	//tests addCandidate to see if the candidate already exists exception works
	@Test (expected = CandidateExistsException.class)
	public void testThrowsCandidateExists () throws CandidateExistsException {
		ElectionFive().addCandidate("Lucas");
	}
	
	//tests the findWinnerMostFirstVotes method with a normal election
	@Test
	public void testfindWinnerMostFirstVote(){
		assertEquals("Joe", ElectionOne().findWinnerMostFirstVotes());
	}
	
	//tests the findWinnerMostFirstVotes method with only one vote casted
	@Test
	public void testfindWinnerMostFirstVote2(){ 
		assertEquals("Superman", ElectionFour().findWinnerMostFirstVotes());
	}
	
	//tests the findWinnerMostFirstVotes method to make sure the runoff exception works properly
	@Test
	public void testfindWinnerMostFirstVote3(){
		assertEquals("Runoff required", ElectionTwo().findWinnerMostFirstVotes());
	}
	
	//tests the findWinnerMostFirstVotes method to when there is a tie (50% and 50%)
	@Test
	public void testfindWinnerMostFirstVote4(){
		assertEquals("Runoff required", ElectionSix().findWinnerMostFirstVotes());
	}

	//tests the findWinnerMostPoints method with a normal election
	@Test
	public void testWinnerMostPoints(){
		assertEquals("Joe", ElectionOne().findWinnerMostPoints());
	}

	//tests the findWinnerMostPoints method with a normal election again
	@Test
	public void testWinnerMostPoints2(){
		assertEquals("Hunter", ElectionTwo().findWinnerMostPoints());
	}

	//tests the findWinnerMostPoints method with only one vote casted
	@Test
	public void testWinnerMostPoints3() { 
		assertEquals("Superman", ElectionFour().findWinnerMostPoints());
	}
	
	//tests the findWinnerMostPoints method when there is a tie (produces one of the winner's, doesn't matter which one)
	@Test
	public void testWinnerMostPoints4() { 
		assertEquals("Vlad", ElectionSix().findWinnerMostPoints());
	}
}
