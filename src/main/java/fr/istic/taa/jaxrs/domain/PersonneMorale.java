package fr.istic.taa.jaxrs.domain;

import javax.persistence.Entity;

@Entity
public class PersonneMorale extends Personne {

	
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
