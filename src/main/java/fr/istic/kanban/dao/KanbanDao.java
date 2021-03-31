package fr.istic.kanban.dao;

import fr.istic.kanban.entity.Kanban;
import jpa.EntityManagerHelper;

import java.util.List;

public class KanbanDao extends AbstractJpaDao<Long, Kanban> {

	public KanbanDao() {
		super(Kanban.class); 
	}

	public List<Kanban> getAllKanban(){
		return EntityManagerHelper.getEntityManager()
				.createNamedQuery("tousleskanbans", Kanban.class)
				.getResultList();
	}
}
