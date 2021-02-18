package fr.istic.kanban.exceptions;

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
 
}
