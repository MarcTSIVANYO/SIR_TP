package fr.istic.kanban.dto;

import java.util.List;

import fr.istic.kanban.entity.Kanban; 
import fr.istic.kanban.entity.User;

public class KanbanDto {

	Long id;
	String nom;
	User admin;   
	public KanbanDto(Long id, String nom, User admin, List<Long>sectionsId) {
		super();
		this.id = id;
		this.nom = nom;
		this.admin = admin; 
	}
	public KanbanDto() { 
	}

	public KanbanDto(Kanban kanban) { 
		super();
		this.id = kanban.getId();
		this.nom = kanban.getNom();
		this.admin = kanban.getAdmin();  
		
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
	
	public Kanban convertToEntity() {
		return new Kanban(this.nom,this.admin);
	} 
}
