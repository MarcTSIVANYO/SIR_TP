package exceptions;

public class CustomException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void isValid(int value) {
		
		if(value <0) {
    		final String error="L'indice du debut ou fin de la selection ne peut pas �tre n�gatif";
	         System.err.println("Error : " +error );
    		throw new IllegalArgumentException(error);
    	}
	}

	public static void notNull(String contenu) {
		if(contenu==null) {
    		throw new NullPointerException("Le contenu du texte ne peut pas �tre null.");
    	}
	}
	
	public static void notNullAndEmpty(String contenu) {
		if(contenu==null || contenu.isEmpty()) {
    		throw new NullPointerException("Le contenu du texte ne peut pas �tre null ou vide.");
    	}
	}

	public static void isMaxLength(int endIndex, int length) {
		 if(endIndex>length) {
			 throw new IndexOutOfBoundsException("La taille de la selection doit �tre inf�rieur � la taille du buffer.");
		 }
		
	}
}
