package fr.istic.kanban.entity;
 
import java.io.Serializable;
import java.sql.Date;
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
public class Fiche  implements Serializable  {  
	long id;
	String libelle;
	String lieu;
	String url;
	Date dateButoire;
	String note;	
	int  dureeminite; 
	User owner;  
	Section section; 
	List<Tag> tags;
	 
	public Fiche(String libelle, String lieu, String url, Date dateButoire, String note, User owner,
			Section section, List<Tag> tags) {
		super();
		this.libelle = libelle;
		this.lieu = lieu;
		this.url = url;
		this.dateButoire = dateButoire;
		this.note = note; 
		this.owner = owner;
		this.section = section;
		this.tags = tags;
	}
	
	public Fiche() { 
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
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getDateButoire() {
		return dateButoire;
	}
	public void setDateButoire(Date dateButoire) {
		this.dateButoire = dateButoire;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getDureeminite() {
		return dureeminite;
	}
	public void setDureeminite(int dureeminite) {
		this.dureeminite = dureeminite;
	}
	@ManyToOne
	public User getOwner() {
		return owner;
	}
	
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	@ManyToOne
	public Section getSection() {
		return section;
	}
	public void setSection(Section section) {
		this.section = section;
	}
	
	@ManyToMany
    @JoinTable( name = "fiche_tags",
                joinColumns = @JoinColumn( name = "fiche_id" ),
                inverseJoinColumns = @JoinColumn( name = "tag_id" )) 
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	@Override
	public String toString() {
		return "Fiche [id=" + id + ", libelle=" + libelle + ", lieu=" + lieu + ", url=" + url + ", dateButoire="
				+ dateButoire + ", note=" + note + ", dureeminite=" + dureeminite + ", owner=" + owner + "]";
	}
	  
}
