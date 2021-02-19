package fr.istic.kanban.res; 
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import fr.istic.kanban.dto.SectionDto; 
import fr.istic.kanban.service.SectionService; 

@Path("/api/section")
public class SectionResource { 
	SectionService sectionService = new SectionService(); 
	@GET
	@Path("/")
	@javax.ws.rs.Produces( MediaType.APPLICATION_JSON)
	public List<SectionDto> getAll(){ 
		return sectionService.findAll(); 
	}
	
	@GET
	@Path("/{id}")
	@javax.ws.rs.Produces( MediaType.APPLICATION_JSON)
	public SectionDto getById(@PathParam("id") Long id){ 
		return sectionService.getById(id); 
	}
	
	@POST 
	@javax.ws.rs.Produces( MediaType.APPLICATION_JSON)
	@javax.ws.rs.Consumes( MediaType.APPLICATION_JSON)
	public SectionDto save(SectionDto sectionDto){ 
		 sectionService.save(sectionDto);
		 return sectionDto;
	}
	
	@PUT 
	@Path("/{id}")
	@javax.ws.rs.Produces( MediaType.APPLICATION_JSON)
	@javax.ws.rs.Consumes( MediaType.APPLICATION_JSON)
	public SectionDto update(SectionDto sectionDto,@PathParam("id") Long id){  
		 return  sectionService.update(id,sectionDto);
	}
	 
	@DELETE
	@Path("/{id}")
	@javax.ws.rs.Produces( MediaType.APPLICATION_JSON)
	public void deleteById(@PathParam("id") Long id){ 
		  sectionService.deleteById(id);; 
	}
 
}
