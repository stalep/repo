package io.hyperfoil.tools.horreum.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import io.hyperfoil.tools.horreum.api.SortDirection;
import io.hyperfoil.tools.horreum.entity.json.ActionDTO;
import io.hyperfoil.tools.horreum.entity.json.AllowedSiteDTO;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

@Path("/api/action")
@Consumes({ MediaType.APPLICATION_JSON})
@Produces(MediaType.APPLICATION_JSON)
public interface ActionService {
   @POST
   ActionDTO add(ActionDTO action);

   @GET
   @Path("{id}")
   ActionDTO get(@PathParam("id") int id);

   @DELETE
   @Path("{id}")
   void delete(@PathParam("id") int id);

   @GET
   @Path("list")
   List<ActionDTO> list(@QueryParam("limit") Integer limit,
                     @QueryParam("page") Integer page,
                     @QueryParam("sort") @DefaultValue("id") String sort,
                     @QueryParam("direction") @DefaultValue("Ascending") SortDirection direction);

   @GET
   @Path("test/{id}")
   List<ActionDTO> getTestActions(@PathParam("id") int testId);

   @GET
   @Path("allowedSites")
   List<AllowedSiteDTO> allowedSites();

   @Consumes("text/plain")
   @POST
   @Path("allowedSites")
   AllowedSiteDTO addSite(@RequestBody(required = true) String prefix);

   @DELETE
   @Path("allowedSites/{id}")
   void deleteSite(@PathParam("id") long id);
}
