package fr.istic.kanban.dao;

import fr.istic.kanban.entity.Fiche; 
public class FicheDao  extends AbstractJpaDao<Long, Fiche> {

	public FicheDao() {
		super(Fiche.class); 
	}

}
