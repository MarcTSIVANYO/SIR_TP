package fr.istic.kanban.exceptions;

import java.util.regex.Pattern;

import javax.ws.rs.NotFoundException;

public class CustomException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void isValid(Long id) { 
		if(id <=0) {
    		final String error="L'id "+id+" est incorrecte";
	         System.err.println("Error : " +error );
    		throw new NotFoundException(error);
    	}
	}

	public static void isValidString(String email) {
		if(email==null) {
    		final String error="L'id "+email+" est incorrecte";
	         System.err.println("Error : " +error );
    		throw new NotFoundException(error);
    	}
	}
	
	public static boolean isValidEmail(String email) 
    { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    } 
  
 
}
