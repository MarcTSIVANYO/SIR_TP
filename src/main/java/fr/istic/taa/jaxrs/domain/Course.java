package fr.istic.taa.jaxrs.domain;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Course {
	
	@Id
	@GeneratedValue
	long id;
	
	String name;
	Date depart;
	Date arrivee;
	
	@OneToMany
	@JoinColumn(name = "courseID")
	List<Bateau> concurrents;
	
	@OneToOne
	ClassementCourse classement;
	
	@OneToMany
	@JoinColumn(name = "courseID")
	List<Epreuve> epreuves;
	

}
