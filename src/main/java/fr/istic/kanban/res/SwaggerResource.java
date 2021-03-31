package fr.istic.kanban.res;

import org.apache.log4j.Logger;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;

@Path("/api")
public class SwaggerResource {

    private static final Logger logger = Logger.getLogger(SwaggerResource.class.getName());

    @GET
    public byte[] Get1() {
        try {
            String path = System.getProperty("user.dir") + "/src/main/webapp/swagger/index.html";
            return Files.readAllBytes(FileSystems.getDefault().getPath(path));
        } catch (IOException e) {
            return null;
        }
    }

    @GET
    @Path("{path:.*}")
    public byte[] Get(@PathParam("path") String path) {
        try {
            String paths = System.getProperty("user.dir") + "/src/main/webapp/swagger/"+path;
            return Files.readAllBytes(FileSystems.getDefault().getPath(paths));
        } catch (IOException e) {
            return null;
        }
    }
}
