package fr.istic.kanban.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException; 
import fr.istic.kanban.dao.UserDao;
import fr.istic.kanban.dto.UserDto; 
import fr.istic.kanban.entity.User; 
import fr.istic.kanban.exceptions.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {
	private final UserDao userDao = new UserDao();
	private static Logger LOGGER = LoggerFactory.getLogger(FicheService.class);
	
	/*
	 * Enregistrer une entit�
	 */
	public void save(UserDto userDto) { 
        User user=new User(userDto.getEmail(),userDto.getName());
        userDao.save(user);
    }	
	
	/*
	 * Recup�rer la liste de l'entit�
	 */
	public List<UserDto> findAll() { 
        List<User> users=userDao.findAll(); 
		List<UserDto> usersDto = new ArrayList<>();  
		users.forEach(user-> usersDto.add(new UserDto(user.getEmail(),user.getName())) ); 
		return usersDto; 
    }	 

	/*
	 * Recup�rer une valeur � partir d'un id
	 * NotFundException
	 */
	public UserDto getById(String email) {
		CustomException.isValidString(email);
		if(!CustomException.isValidEmail(email)) {
			LOGGER.error("Error : Le format email "+email+" est incorrect");
			throw new NotFoundException("Le format email "+email+" est incorrect");
		}
		UserDto userDto; 
		try {
			User user=userDao.findOne(email);
			if(user==null) { 
				throw new NotFoundException("Aucun resultat pour l'�lement avec l'identifiant "+email);
			}
			userDto=new UserDto(user.getEmail(),user.getName());
        }catch (Exception e){
			LOGGER.error("Error : " +e.getMessage());
			throw new NotFoundException("Aucun resultat pour l'�lement avec l'identifiant "+email);
        } 
		return userDto; 
	}

	/*
	 * Recup�rer une valeur � partir d'un id
	 * NotFundException if id is'nt valid or not found 
	 */
	public UserDto update(String email, UserDto userDto) {
		CustomException.isValidString(email);
		if(!CustomException.isValidEmail(email)) { 
			throw new NotFoundException("Le format email "+email+" est incorrect");
		} 
		if(!CustomException.isValidEmail(userDto.getEmail())) { 
			throw new NotFoundException("Le format email "+userDto.getEmail()+" est incorrect");
		}
		try {
			User user=userDao.findOne(email);
			if(user==null) { 
				throw new NotFoundException("Aucun resultat pour l'�lement avec l'identifiant "+email);
			}
			
			user.setEmail(userDto.getEmail() );
			user.setName(userDto.getName()); 
			userDao.update(user); 
        }catch (Exception e){
			LOGGER.error("Error : " +e.getMessage());
			throw new NotFoundException("Error : "+e.getMessage());
        } 
		return userDto; 
	}
	/*
	 * Supprimer une entit�
	 * NotFundException if id is'nt valid or not found 
	 */
	public void deleteById(String email) {
		CustomException.isValidString(email);
		if(!CustomException.isValidEmail(email)) { 
			throw new NotFoundException("Le format email "+email+" est incorrect");
		}  
		userDao.deleteById(email);
	} 
}
