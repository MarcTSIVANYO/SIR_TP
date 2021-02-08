package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Tag  implements Serializable { 
	long id;
	String name;
	Fiche fiche;
	
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
	

	@ManyToOne
	public Fiche getFiche() {
		return fiche;
	}
	public void setFiche(Fiche fiche) {
		this.fiche = fiche;
	}
	@Override
	public String toString() {
		return "Tag [id=" + id + ", name=" + name + "]";
	}
	 
}
