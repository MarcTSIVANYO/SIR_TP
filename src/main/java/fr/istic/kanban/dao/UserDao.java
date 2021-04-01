package fr.istic.kanban.dao;
 
import java.util.List;
 
import fr.istic.kanban.entity.User;
import jpa.EntityManagerHelper;

public  class UserDao extends AbstractJpaDao<Long, User> {

	public UserDao() {
		super(User.class); 
	}
	
	public User findByEmail(String email) {
		return EntityManagerHelper.getEntityManager()
				.createNamedQuery("User.findByEmail", User.class)
				.setParameter("email", email)
				.getSingleResult(); 
	} 
 
}
