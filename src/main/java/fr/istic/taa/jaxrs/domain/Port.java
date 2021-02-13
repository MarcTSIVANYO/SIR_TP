package fr.istic.taa.jaxrs.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
public class Port {
	@Id
	@GeneratedValue
	long id;
	
	String name;
	String adresse;
	
}
