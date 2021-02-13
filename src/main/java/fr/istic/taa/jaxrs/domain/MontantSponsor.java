package fr.istic.taa.jaxrs.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MontantSponsor {

	
	@Id
	long id;
	long montant;
	
	@ManyToOne
	Bateau b;
	@ManyToOne
	Sponsor s;
	
}
