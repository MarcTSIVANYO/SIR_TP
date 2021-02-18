package fr.istic.kanban.dto;

import java.util.List;
 
import fr.istic.kanban.entity.Fiche;

public class TagDto {
	String name;
	List<Fiche> fiches;
	
	public TagDto( String name, List<Fiche> fiches) {
		super(); 
		this.name = name;
		this.fiches = fiches;
	}
	
	public TagDto() { 
	}
 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	  
	public List<Fiche> getFiche() {
		return fiches;
	}
	public void setFiche(List<Fiche> fiches) {
		this.fiches = fiches;
	}
}
