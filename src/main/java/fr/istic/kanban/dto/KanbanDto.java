package fr.istic.kanban.dto;

import java.util.List;

import fr.istic.kanban.entity.Section;
import fr.istic.kanban.entity.User;

public class KanbanDto {

	Long id;
	String nom;
	User admin; 
	//List<Section> sections;
	public KanbanDto(Long id, String nom, User admin/*, List<Section> sections*/) {
		super();
		this.id = id;
		this.nom = nom;
		this.admin = admin;
		//this.sections = sections;
	}
	public KanbanDto() { 
	}

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
	public User getAdmin() {
		return admin;
	}
	public void setAdmin(User admin) {
		this.admin = admin;
	}
	/*public List<Section> getSections() {
		return sections;
	}
	public void setSections(List<Section> sections) {
		this.sections = sections;
	}*/
}
