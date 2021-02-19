package fr.istic.kanban.dto;
 
public class TagDto {
	Long id;
	String name; 
	
	public TagDto(Long id, String name) {
		super(); 
		this.id=id;
		this.name = name; 
	}
	
	public TagDto() { 
	}
	  
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

	@Override
	public String toString() {
		return "TagDto [id=" + id + ", name=" + name + "]";
	}
	   
}
