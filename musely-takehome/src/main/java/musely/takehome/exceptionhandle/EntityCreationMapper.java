package musely.takehome.exceptionhandle;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Provider
public class EntityCreationMapper implements ExceptionMapper<InternalServerErrorException>{

	ObjectMapper mapper = new ObjectMapper();

	@Override
	public Response toResponse(InternalServerErrorException exception) {
		ObjectNode error = mapper.createObjectNode();
		error.put("error",exception.getMessage());
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).type(MediaType.APPLICATION_JSON).build();
	}

}
