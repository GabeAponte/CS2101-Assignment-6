/**
 * A class that holds the properties 
 * and methods for the CandidateExistsException
 * exception
 * 
 * @author Gabe Aponte
 */
public class CandidateExistsException extends Exception {
	private String name;
	
	CandidateExistsException(String name){
		this.name = name;
	}
	
	/**
	 * This is the getCandidateName method which produces the
	 * the value of the name field
	 * 
	 * @return name  a string representing a name
	 */
	public String getCandidateName() {
		return this.name;
	}
}
