package fr.istic.kanban.dto;

import java.sql.Date;
import java.util.List;

import fr.istic.kanban.entity.Section;
import fr.istic.kanban.entity.Tag;
import fr.istic.kanban.entity.User;

public class FicheDto {
	Long id;
	String libelle;
	String lieu;
	String url;
	Date dateButoire;
	String note;	
	int  dureeminite; 
	Long sectionId;
	UserDto owner;   
	List<TagDto> tags;
	public FicheDto(Long id, String libelle, String lieu, String url, Date dateButoire, String note, Long sectionId,  UserDto owner) {
		super();
		this.id=id;
		this.libelle = libelle;
		this.lieu = lieu;
		this.url = url;
		this.dateButoire = dateButoire;
		this.note = note; 
		this.owner = owner; 
		this.sectionId=sectionId;
	}

	public FicheDto(Long id, String libelle, String lieu, String url, String note, Long sectionId){
		super();
		this.id=id;
		this.libelle = libelle;
		this.lieu = lieu;
		this.url = url;
		this.note = note;
		this.sectionId=sectionId;
	}
 
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FicheDto() { 
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


	public UserDto getOwner() {
		return owner;
	}


	public void setOwner(UserDto owner) {
		this.owner = owner;
	}


	public List<TagDto> getTags() {
		return tags;
	}


	public void setTags(List<TagDto> tags) {
		this.tags = tags;
	}


	public Long getSectionId() {
		return sectionId;
	}


	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}
 
	
	 
}
