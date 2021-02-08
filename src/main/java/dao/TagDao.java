package dao;

import entity.Tag;

public class TagDao extends AbstractJpaDao<Long, Tag> {

	public TagDao() {
		super(Tag.class); 
	}

}