package fr.istic.kanban.dto;

import fr.istic.kanban.entity.Kanban;

public class SectionDto {
	Long id;
	String libelle;
	int position; 
	KanbanDto kanban;
	
	public SectionDto(Long id, String libelle, int position, KanbanDto kanban) { 
		this.id=id;
		this.position=position;
		this.libelle=libelle;
		this.kanban=kanban;
	}
	
	public SectionDto() { 
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public KanbanDto getKanban() {
		return kanban;
	}

	public void setKanban(KanbanDto kanban) {
		this.kanban = kanban;
	}

	@Override
	public String toString() {
		return "SectionDto [libelle=" + libelle + ", position=" + position + ", kanban=" + kanban + "]";
	}
		
}
