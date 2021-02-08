package dao;

import entity.Kanban;

public class KanbanDao extends AbstractJpaDao<Long, Kanban> {

	public KanbanDao() {
		super(Kanban.class); 
	}
	
}
