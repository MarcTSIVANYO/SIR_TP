package fr.istic.taa.jaxrs.dto;

public class BateauDTO {

	String nom;
	double taille;
	String skipperName;
	
	public String getSkipperName() {
		return skipperName;
	}
	public void setSkipperName(String skipperName) {
		this.skipperName = skipperName;
	}

	public BateauDTO(String nom, double taille, String skipperName) {
		super();
		this.nom = nom;
		this.taille = taille;
		this.skipperName = skipperName;
	}
	public BateauDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getTaille() {
		return taille;
	}

	public void setTaille(double taille) {
		this.taille = taille;
	}


}
