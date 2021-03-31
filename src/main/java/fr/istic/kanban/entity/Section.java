package fr.istic.kanban.entity;
 

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_SECTION")
public class Section implements Serializable {

	long id;
	String libelle;
	int position; 
	Kanban kanban;
	
	public Section(String libelle, int position, Kanban kanban) {
		this.position=position;
		this.libelle=libelle;
		this.kanban=kanban;
	}
	
	public Section() {
		
	}
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	 @ManyToOne
	public Kanban getKanban() {
		return kanban;
	}

	public void setKanban(Kanban kanban) {
		this.kanban = kanban;
	}

	/*@OneToMany(mappedBy = "section", cascade = CascadeType.PERSIST)
	public List<Fiche> getFiches() {
		return fiches;
	}

	public void setFiches(List<Fiche> fiches) {
		this.fiches = fiches;
	}*/

	@Override
	public String toString() {
		return "Section [id=" + id + ", libelle=" + libelle + ", positions=" + position + "]";
	}
	  

}
