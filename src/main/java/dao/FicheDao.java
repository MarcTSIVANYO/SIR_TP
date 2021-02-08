package dao;

import entity.Fiche; 
public class FicheDao  extends AbstractJpaDao<Long, Fiche> {

	public FicheDao() {
		super(Fiche.class); 
	}

}
