package fr.istic.kanban.dao;
 
import fr.istic.kanban.entity.User;

public class UserDao extends AbstractJpaDao<String, User> {

	public UserDao() {
		super(User.class); 
	}

}
