package fr.istic.kanban.dao;

import fr.istic.kanban.entity.Section;

public class SectionDao extends AbstractJpaDao<Long, Section> {

	public SectionDao() {
		super(Section.class); 
	}

}
