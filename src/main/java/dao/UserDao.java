package dao;
 
import entity.User;

public class UserDao extends AbstractJpaDao<String, User> {

	public UserDao() {
		super(User.class); 
	}

}
