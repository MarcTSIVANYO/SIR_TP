package fr.istic.kanban.dto;

import fr.istic.kanban.entity.User;

public class UserDto {
	String email;
	String name;
	String password;
	 
	
	public UserDto(String email, String name,String password) {
		super();
		this.email = email;
		this.name = name;
		this.password=password;
	}
	
	public UserDto(String email, String name) {
		super();
		this.email = email;
		this.name = name; 
	}
	
	public UserDto() {
		super(); 
	}

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
		return "UserDto [email=" + email + ", name=" + name + "]";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public User convertToEntity() {
		return new User(this.email, this.name);
	}
}
