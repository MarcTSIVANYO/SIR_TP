package fr.istic.kanban.resource;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import fr.istic.kanban.dto.KanbanDto;
import fr.istic.kanban.dto.SectionDto;
import fr.istic.kanban.service.KanbanService; 
 
@Path("/api/kanban")
public class KanbanResource { 
	KanbanService kanbanService = new KanbanService(); 
	@GET
	@Path("/")
	@javax.ws.rs.Produces( MediaType.APPLICATION_JSON)
	public List<KanbanDto> getAll(){ 
		return kanbanService.findAll(); 
	}
	
	@GET
	@Path("/{id}")
	@javax.ws.rs.Produces( MediaType.APPLICATION_JSON)
	public KanbanDto getById(@PathParam("id") Long id){ 
		return kanbanService.getById(id); 
	}
	
	@POST 
	@javax.ws.rs.Produces( MediaType.APPLICATION_JSON)
	@javax.ws.rs.Consumes( MediaType.APPLICATION_JSON)
	public KanbanDto save(KanbanDto kanbanDto){
		  kanbanService.save(kanbanDto);
		 return kanbanDto;
	}
	
	@PUT 
	@Path("/{id}")
	@javax.ws.rs.Produces( MediaType.APPLICATION_JSON)
	@javax.ws.rs.Consumes( MediaType.APPLICATION_JSON)
	public KanbanDto update(KanbanDto kanbanDto,@PathParam("id") Long id){  
		 return  kanbanService.update(id,kanbanDto);
	}
	 
	@DELETE
	@Path("/{id}")
	@javax.ws.rs.Produces( MediaType.APPLICATION_JSON)
	public void deleteById(@PathParam("id") Long id){ 
		  kanbanService.deleteById(id);; 
	}

	@GET
	@Path("/{id}/sections")
	@javax.ws.rs.Produces( MediaType.APPLICATION_JSON)
	public List<SectionDto> getSectionById(@PathParam("id") Long id){ 
		return kanbanService.getSectionById(id); 
	}
}
