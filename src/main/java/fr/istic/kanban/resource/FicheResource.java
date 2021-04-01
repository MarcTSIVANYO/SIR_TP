package fr.istic.kanban.resource;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType; 
import fr.istic.kanban.dto.FicheDto;
import fr.istic.kanban.service.FicheService;

@Path("/api/fiches")
public class FicheResource {

	FicheService ficheService = new FicheService(); 
	@GET
	@Path("/")
	@javax.ws.rs.Produces( MediaType.APPLICATION_JSON)
	public List<FicheDto> getAll(){ 
		return ficheService.findAll(); 
	}
	
	@GET
	@Path("/{id}")
	@javax.ws.rs.Produces( MediaType.APPLICATION_JSON)
	public FicheDto getById(@PathParam("id") Long id){ 
		return ficheService.getById(id); 
	}
	
	@POST 
	@javax.ws.rs.Produces( MediaType.APPLICATION_JSON)
	@javax.ws.rs.Consumes( MediaType.APPLICATION_JSON)
	public FicheDto save(FicheDto ficheDto){ 
		 ficheService.save(ficheDto);
		 return ficheDto;
	}
	
	@PUT 
	@Path("/{id}")
	@javax.ws.rs.Produces( MediaType.APPLICATION_JSON)
	@javax.ws.rs.Consumes( MediaType.APPLICATION_JSON)
	public FicheDto update(FicheDto ficheDto,@PathParam("id") Long id){  
		 return  ficheService.update(id,ficheDto);
	}
	 
	@DELETE
	@Path("/{id}")
	@javax.ws.rs.Produces( MediaType.APPLICATION_JSON)
	public void deleteById(@PathParam("id") Long id){ 
		  ficheService.deleteById(id);; 
	} 
}


