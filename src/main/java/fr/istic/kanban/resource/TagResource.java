package fr.istic.kanban.resource;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import fr.istic.kanban.dto.TagDto;
import fr.istic.kanban.service.TagService; 

@Path("/api/tag")
public class TagResource { 
	TagService tagService = new TagService(); 
	@GET
	@Path("/")
	@javax.ws.rs.Produces( MediaType.APPLICATION_JSON)
	public List<TagDto> getAll(){ 
		return tagService.findAll(); 
	}
	
	@GET
	@Path("/{id}")
	@javax.ws.rs.Produces( MediaType.APPLICATION_JSON)
	public TagDto getById(@PathParam("id") Long id){ 
		return tagService.getById(id); 
	}
	
	@POST 
	@javax.ws.rs.Produces( MediaType.APPLICATION_JSON)
	@javax.ws.rs.Consumes( MediaType.APPLICATION_JSON)
	public TagDto save(TagDto tagDto){ 
		 tagService.save(tagDto);
		 return tagDto;
	}
	
	@PUT 
	@Path("/{id}")
	@javax.ws.rs.Produces( MediaType.APPLICATION_JSON)
	@javax.ws.rs.Consumes( MediaType.APPLICATION_JSON)
	public TagDto update(TagDto tagDto,@PathParam("id") Long id){  
		 return  tagService.update(id,tagDto);
	}
	 
	@DELETE
	@Path("/{id}")
	@javax.ws.rs.Produces( MediaType.APPLICATION_JSON)
	public void deleteById(@PathParam("id") Long id){ 
		  tagService.deleteById(id);; 
	}
 
}

