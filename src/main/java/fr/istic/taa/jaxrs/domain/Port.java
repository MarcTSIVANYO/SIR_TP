package fr.istic.taa.jaxrs.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id; 

@Entity
public class Port {
	@Id
	@GeneratedValue
	long id;
	
	String name;
	String adresse;
	
}
