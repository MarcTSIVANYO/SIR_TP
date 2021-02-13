package fr.istic.taa.jaxrs.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import fr.istic.taa.jaxrs.dto.BateauDTO;
import fr.istic.taa.jaxrs.service.BateauService;

@Path("/api/bateau")
public class BateauResource {
	
	BateauService s = new BateauService();
	
	
	@GET
	@Path("/{name}")
	@javax.ws.rs.Produces( MediaType.APPLICATION_JSON)
	public List<BateauDTO> getBateauByName(@PathParam("name") String name){
		
		return s.getBateauByName(name);
	
		
	}
	
	@POST
	@javax.ws.rs.Produces( MediaType.APPLICATION_JSON)
	@javax.ws.rs.Consumes( MediaType.APPLICATION_JSON)
	public BateauDTO createBateau(BateauDTO b){
		
		
		return s.createBateau(b);
	
		
	}

}
