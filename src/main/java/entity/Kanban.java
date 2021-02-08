package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany; 
/**
 * 
 */ 
@Entity
public class Kanban implements Serializable {

 
	long id;
	String nom;
	User admin;
	
	List<Section> sections;
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	

	@ManyToOne
	public User getAdmin() {
		return admin;
	}
	
	public void setAdmin(User admin) {
		this.admin = admin;
	}
	
	@OneToMany
	public List<Section> getSections() {
		return sections;
	}
	public void setSections(List<Section> sections) {
		this.sections = sections;
	}
	@Override
	public String toString() {
		return "Kanban [id=" + id + ", nom=" + nom +  ", admin=" + admin.getEmail() + "]";
	}
	  	 

}
