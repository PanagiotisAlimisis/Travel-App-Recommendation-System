package myexceptions;

public class NoSuchCityException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSuchCityException(String message) {
		super();
		System.out.println("There is no city \""+message+"\"!");
	}
}

