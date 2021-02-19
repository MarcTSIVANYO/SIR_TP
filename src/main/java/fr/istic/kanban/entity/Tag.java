package fr.istic.kanban.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Tag  implements Serializable { 
	long id;
	String name;
	List<Fiche> fiches;
	
	public Tag( String name, List<Fiche> fiches) {
		super(); 
		this.name = name;
		this.fiches = fiches;
	}

	public Tag( String name) {
		super(); 
		this.name = name; 
	}
	public Tag() { 
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	 
	@ManyToMany(mappedBy="tags")
	public List<Fiche> getFiche() {
		return fiches;
	}
	public void setFiche(List<Fiche> fiches) {
		this.fiches = fiches;
	}
	@Override
	public String toString() {
		return "Tag [id=" + id + ", name=" + name + "]";
	} 
}
