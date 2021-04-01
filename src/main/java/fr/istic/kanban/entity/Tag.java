package fr.istic.kanban.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Tag implements Serializable {
	Long id;
	String name;
	@ElementCollection(targetClass = Fiche.class)
	List<Fiche> fiches = new ArrayList<>();

	public Tag(String name, List<Fiche> fiches) {
		super();
		this.name = name;
		this.fiches = fiches;
	}

	public Tag(String name) {
		super();
		this.name = name;
	}

	public Tag() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(mappedBy = "tags")
	public List<Fiche> getFiche() {
		return fiches;
	}

	public void setFiche(List<Fiche> fiches) {
		this.fiches = fiches;
	}

	public void addFiche(Fiche fiche) {
		this.fiches.add(fiche);
		fiche.addTag(this);
	}

	@Override
	public String toString() {
		return "Tag [id=" + id + ", name=" + name + "]";
	}
}
