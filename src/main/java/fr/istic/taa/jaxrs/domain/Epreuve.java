package fr.istic.taa.jaxrs.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Epreuve {
	@Id
	@GeneratedValue
	long id;
	
	
	Date dateEpreuve;
	
	@ManyToOne
	Port depart;
	@ManyToOne
	Port arrivee;
	
	@OneToOne
	ClassementEpreuve classement;
}
