package fr.istic.kanban.entity;

import java.io.Serializable;
import java.util.ArrayList;
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
 
	Long id;
	String nom;
	User admin;


	@ElementCollection(targetClass = Section.class)
	List<Section> sections = new ArrayList<>();
	
	public Kanban(String nom, User admin) {
		super();
		this.nom = nom;
		this.admin = admin;
	}

	
	public Kanban() { 
	}

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() { 
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	

	@ManyToOne(cascade = {CascadeType.MERGE})
	public User getAdmin() {
		return admin;
	}
	
	public void setAdmin(User admin) {
		this.admin = admin;
	}

	@OneToMany(mappedBy = "kanban", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public void add(Section section) {
		this.sections.add(section);
		section.setKanban(this);
	}
	@Override
	public String toString() {
		return "Kanban [id=" + id + ", nom=" + nom +  ", admin=" + admin.getEmail() + "]";
	}
	 
}
