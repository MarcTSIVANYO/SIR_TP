package fr.istic.kanban.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * 
 */  
@Entity
@NamedQueries({
		@NamedQuery(name="tousleskanbans", query = "select k from Kanban as k")
})
public class Kanban implements Serializable {
 
	long id;
	String nom;
	User admin; 
	List<Section> sections;
	
	public Kanban(String nom, User admin) {
		super();
		this.nom = nom;
		this.admin = admin;
	}
	
	public Kanban() { 
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	
	@OneToMany(mappedBy = "kanban",cascade = CascadeType.PERSIST)
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
