/**
 * A class that holds the properties 
 * and methods for the UnknownCandidateException
 * exception
 * 
 * @author Gabe Aponte
 */
class UnknownCandidateException extends Exception  {
	private String DNEName;

	UnknownCandidateException(String DNEName){
		this.DNEName = DNEName;
	}
	
	/**
	 * This is the UnknownCandidateException method which produces the
	 * the value of the DNEName field
	 * 
	 * @return DNEName  a string representing a name that doesn't exist in the election
	 */
	public String getDNEName(){
		return this.DNEName;	
	}
}
