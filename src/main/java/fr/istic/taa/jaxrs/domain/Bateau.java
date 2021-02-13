package fr.istic.taa.jaxrs.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Bateau {

	
	@Id
	@GeneratedValue
	long id;

	String nom;
	
	double taille;
	
	
	@OneToMany(mappedBy = "b")
	List<MontantSponsor> montantsponsor;
	
	@OneToMany
	@JoinColumn(name = "equipierID")
	List<PersonnePhysique> equipiers;
	@ManyToOne
	PersonnePhysique skipper;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public List<MontantSponsor> getMontantsponsor() {
		return montantsponsor;
	}
	public void setMontantsponsor(List<MontantSponsor> montantsponsor) {
		this.montantsponsor = montantsponsor;
	}
	public List<PersonnePhysique> getEquipiers() {
		return equipiers;
	}
	public void setEquipiers(List<PersonnePhysique> equipiers) {
		this.equipiers = equipiers;
	}
	public PersonnePhysique getSkipper() {
		return skipper;
	}
	public void setSkipper(PersonnePhysique skipper) {
		this.skipper = skipper;
	}
	
	
	
}
