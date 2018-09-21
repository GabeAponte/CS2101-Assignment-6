/**
 * A class that holds the properties 
 * and methods for the DuplicateVotesException
 * exception
 * 
 * @author Gabe Aponte
 */
class DuplicateVotesException extends Exception {
	private String duplicateName;
	
	DuplicateVotesException(String duplicateName){
		this.duplicateName = duplicateName;
	}
	
	/**
	 * This is the getDuplicateName method which produces the
	 * the value of the duplicateName field
	 * 
	 * @return duplicateName  a string representing a name that had been used before
	 */
	public String getDuplicateName() {
		return this.duplicateName;
	}
}
