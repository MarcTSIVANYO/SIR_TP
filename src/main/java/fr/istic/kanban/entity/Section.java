package fr.istic.kanban.entity;
 

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_SECTION")
public class Section implements Serializable {

	Long id;
	String libelle;
	int position; 
	Kanban kanban;

	@ElementCollection(targetClass = Fiche.class)
	List<Fiche> fiches = new ArrayList<>();
	
	public Section(String libelle, int position, Kanban kanban) {
		this.position=position;
		this.libelle=libelle;
		this.kanban=kanban;
	}
	
	public Section() {
		
	}
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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

	@ManyToOne(cascade = CascadeType.ALL)
	public Kanban getKanban() {
		return kanban;
	}

	public void setKanban(Kanban kanban) {
		this.kanban = kanban;
	}

	@OneToMany(mappedBy = "section", cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	public List<Fiche> getFiches() {
		return fiches;
	}

	public void setFiches(List<Fiche> fiches) {
		this.fiches = fiches;
	}

	@Override
	public String toString() {
		return "Section [id=" + id + ", libelle=" + libelle + ", positions=" + position + "]";
	}

	public void addFiche(Fiche fiche) {
		this.fiches.add(fiche);
		fiche.setSection(this);
	}
}
