package fr.istic.kanban.dao;

import fr.istic.kanban.entity.Tag;

public class TagDao extends AbstractJpaDao<Long, Tag> {

	public TagDao() {
		super(Tag.class); 
	}

}