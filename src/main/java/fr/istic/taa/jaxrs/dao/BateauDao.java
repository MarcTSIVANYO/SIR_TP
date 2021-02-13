package fr.istic.taa.jaxrs.dao;

import java.util.List;

import fr.istic.taa.jaxrs.domain.Bateau;

public class BateauDao extends GenericDaoJpaImpl<Bateau, Long>{
	
	public List<Bateau> getBateauByName(String name){
		
		return this.entityManager.createQuery("select b from Bateau as b where b.nom = :name")
			.setParameter("name", name).getResultList();
		
	}

}
