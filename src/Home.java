import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class Home {
    public static Event event;

    @GET
    @Produces("text/json")
    @Path("event")
    public Response getEvent() {
        return Response.ok(Event.text).build();
    }

    @POST
    @Path("event")
    //@Consumes(MediaType.)
    public Response newEvent(@FormParam("text") String text){
        Event.text = text;
        return Response.noContent().build();
    }
}