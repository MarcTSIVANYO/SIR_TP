package fr.istic.kanban.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User  implements Serializable  {
 
	String email;
	String name;
	
	
	public User(String email, String name) {
		super();
		this.email = email;
		this.name = name;
	}
	
	public User() { 
	}

	@Id
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
	 
}
