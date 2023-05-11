package musely.takehome.exceptionhandle;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javax.persistence.EntityNotFoundException;

@Provider
public class EntityNotFoundMapper implements ExceptionMapper<EntityNotFoundException> {

	ObjectMapper mapper = new ObjectMapper();

	@Override
	public Response toResponse(EntityNotFoundException exception) {
    	
		ObjectNode error = mapper.createObjectNode();
		error.put("error", "Given User Not Found!!");
		return Response.status(Response.Status.NOT_FOUND).entity(error).type(MediaType.APPLICATION_JSON).build();
	}

}
