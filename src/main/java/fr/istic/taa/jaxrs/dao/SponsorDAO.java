package fr.istic.taa.jaxrs.dao;

import java.util.List;

import fr.istic.taa.jaxrs.domain.Sponsor;

public class SponsorDAO extends GenericDaoJpaImpl<Sponsor, Long>{
	public List<Sponsor> getSponsorByName(String name){
		
		return this.entityManager.createQuery("select b from Sponsor as b where b.name = :name")
			.setParameter("name", name).getResultList();
		
	}


}
