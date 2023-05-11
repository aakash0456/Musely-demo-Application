package musely.takehome.exceptionhandle;

import java.sql.SQLException;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SQLExceptionMapper implements ExceptionMapper<EntityExistsException>{
	ObjectMapper mapper = new ObjectMapper();

	

	@Override
	public Response toResponse(EntityExistsException exception) {
		ObjectNode error = mapper.createObjectNode();
		error.put("error", "Given User Not Found!!");
		return Response.status(Response.Status.NOT_FOUND).entity(error).type(MediaType.APPLICATION_JSON).build();
	}
}
