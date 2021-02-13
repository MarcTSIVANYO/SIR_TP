package fr.istic.taa.jaxrs.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class ClassementEpreuve {

	@Id
	@GeneratedValue
	long id;
	
	@OneToMany
	@JoinColumn(name ="epreuveID")
	List<Bateau> ordrebateau;
	
}
