package musely.takehome.rest;

import musely.takehome.dal.model.User;
import musely.takehome.service.UserService;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ser.impl.UnknownSerializer;

@Path("/users")
public class UserEndpoint {

	private UserService userService = new UserService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAll() {
		try {
			return userService.getAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new InternalServerErrorException(e.toString());
		}
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getById(@PathParam("id") Long id) {

		try {
			User user = userService.getById(id);
			if (user != null)
				return user;
			else
				throw new EntityNotFoundException("Given user not found!!");
		} catch (Exception e) {
			throw new InternalServerErrorException(e.toString());
		}

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/insert")
	public User insert(User client) {
		try {
			return userService.insert(client);
		} catch (Exception e) {
			throw new InternalServerErrorException(e.toString());
		}

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/insertall")
	public void insert(List<User> clientList) {
		try {
			userService.saveList(clientList);
		} catch (Exception e) {
			throw new InternalServerErrorException(e.getMessage());
		}
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/delete/{id}")
	public User delete(@PathParam("id") Long id) {
		try {
			return userService.delete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new InternalServerErrorException(e.getMessage());
		}

	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/update")
	public void update(User client) {
		try {
			userService.update(client);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new InternalServerErrorException(e.getMessage());
		}
	}

}
