package fr.istic.kanban.res;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import fr.istic.kanban.dto.UserDto;
import fr.istic.kanban.service.UserService;

@Path("/api/users") 
public class UserResource { 
	UserService userService = new UserService(); 
	@GET
	@Path("/")
	@javax.ws.rs.Produces( MediaType.APPLICATION_JSON)
	public List<UserDto> getAll(){ 
		return userService.findAll(); 
	}
	
	@GET
	@Path("/{email}")
	@javax.ws.rs.Produces( MediaType.APPLICATION_JSON)
	public UserDto getById(@PathParam("email") String email){ 
		return userService.getByEmail(email); 
	}
	
	@POST 
	@javax.ws.rs.Produces( MediaType.APPLICATION_JSON)
	@javax.ws.rs.Consumes( MediaType.APPLICATION_JSON)
	public UserDto save(UserDto userDto){ 
		 userService.save(userDto);
		 return userDto;
	}
	
	@PUT 
	@Path("/{email}")
	@javax.ws.rs.Produces( MediaType.APPLICATION_JSON)
	@javax.ws.rs.Consumes( MediaType.APPLICATION_JSON)
	public UserDto update(UserDto userDto,@PathParam("email") String email){  
		 return  userService.update(email,userDto);
	}
	 
	@DELETE
	@Path("/{email}")
	@javax.ws.rs.Produces( MediaType.APPLICATION_JSON)
	public void deleteById(@PathParam("email") String email){ 
		  userService.deleteById(email);; 
	}
 
}

