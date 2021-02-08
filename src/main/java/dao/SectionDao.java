package dao;

import entity.Section;

public class SectionDao extends AbstractJpaDao<Long, Section> {

	public SectionDao() {
		super(Section.class); 
	}

}
