package fr.istic.kanban.dao;

import fr.istic.kanban.entity.Fiche;
import jpa.EntityManagerHelper;

import java.util.List;

public class FicheDao  extends AbstractJpaDao<Long, Fiche> {

	public FicheDao() {
		super(Fiche.class); 
	}

	public List<Fiche> getAllFiches(){
		return EntityManagerHelper.getEntityManager()
				.createNamedQuery("touteslesfiches", Fiche.class)
				.getResultList();
	}
}
