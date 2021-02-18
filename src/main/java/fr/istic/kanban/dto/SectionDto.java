package fr.istic.kanban.dto;

import fr.istic.kanban.entity.Kanban;

public class SectionDto {
	String libelle;
	int position; 
	Kanban kanban;
	
	public SectionDto(String libelle, int position, Kanban kanban) { 
		this.position=position;
		this.libelle=libelle;
		this.kanban=kanban;
	}
	
	public SectionDto() { 
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Kanban getKanban() {
		return kanban;
	}

	public void setKanban(Kanban kanban) {
		this.kanban = kanban;
	}
		
}
