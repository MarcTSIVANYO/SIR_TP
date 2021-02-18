package fr.istic.kanban.dao;

import fr.istic.kanban.entity.Kanban;

public class KanbanDao extends AbstractJpaDao<Long, Kanban> {

	public KanbanDao() {
		super(Kanban.class); 
	}
	
}
