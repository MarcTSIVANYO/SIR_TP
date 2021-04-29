package fr.istic.kanban.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id; 
import javax.persistence.NamedQuery;

import org.mindrot.jbcrypt.BCrypt;

import fr.istic.kanban.dto.UserDto;

@Entity
@NamedQuery(name="User.findByEmail",
query="SELECT u FROM User u WHERE u.email = :email")
public class User  implements Serializable  { 
	Long id;
	String email;
	String name;
	String password;
	

	public User(String email, String name) {
		super();
		this.email = email;
		this.name = name;
	}
	
	public User(String email, String name, String password) {
		super();
		this.email = email;
		this.name = name;
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());;
	}

	public User() { 
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(unique=true)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "User [email=" + email + ", name=" + name + "]";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password =  BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
}
