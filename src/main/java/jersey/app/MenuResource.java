
package jersey.app;

import java.util.List;

import org.glassfish.grizzly.http.util.HttpStatus;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HEAD;
import jakarta.ws.rs.OPTIONS;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;

/**
 * Resource class for {@link Menu} Object that exposes REST endpoints
 * 
 * @author siddharthagit
 */
@Path("/rest")
public class MenuResource {
	MenuService service = MenuService.getInstance();

	@GET
	@Path("/menus")
	@Produces("application/json")
	public List<Menu> getMenus() {
		return service.getAll();
	}

	@GET
	@Path("/menus/{menu_ID}")
	@Produces("application/json")
	public Response getMenu(@PathParam("menu_ID") Long menuId) {
		try {
			return Response.ok(service.get(menuId)).build();
		} catch (Exception e) {
			return Response.serverError().status(HttpStatus.BAD_REQUEST_400.getStatusCode(), e.getMessage()).build();
		}
	}

	@POST
	@Path("/menus")
	@Produces("application/json")
	public Response add(Menu c) {
		service.add(c);
		return Response.ok(c).status(HttpStatus.CREATED_201.getStatusCode(), "Created").build();
	}

	@PUT
	@Path("/menus/{menu_ID}")
	@Produces("application/json")
	public Response update(@PathParam("menu_ID") Long menuId, Menu c) {
		try {
			return Response.ok(service.update(menuId,c.getName())).build();
		} catch (Exception e) {
			return Response.serverError().status(HttpStatus.BAD_REQUEST_400.getStatusCode(), e.getMessage()).build();
		}
	}

	@PATCH
	@Path("/menus/{menu_ID}")
	@Produces("application/json")
	public Response update2(@PathParam("menu_ID") Long menuId, @QueryParam("name") String name) {
		try {
			return Response.ok(service.update(menuId,name)).build();
		} catch (Exception e) {
			return Response.serverError().status(HttpStatus.BAD_REQUEST_400.getStatusCode(), e.getMessage()).build();
		}
	}

	@DELETE
	@Path("/menus/{menu_ID}")
	@Produces("application/json")
	public Response delete(@PathParam("menu_ID") Long menuId) {
		try {
			service.delete(menuId);
			return Response.ok().status(HttpStatus.OK_200.getStatusCode()).build();
		} catch (Exception e) {
			return Response.serverError().status(HttpStatus.BAD_REQUEST_400.getStatusCode(), e.getMessage()).build();
		}
	}

	@OPTIONS
	@Path("/menus")
	@Produces("text/plain")
	public String touch() {
		return "options";
	}

	@HEAD
	@Path("/menus")
	@Produces("text/plain")
	public String head() {
		return "head";
	}

}
