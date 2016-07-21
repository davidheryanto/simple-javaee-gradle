import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Date;

@Path("/hello")
public class HelloService {
    @GET
    public Response get() {
        return Response.ok(String.format("[%s] Hello World Yoo", new Date())).build();
    }
}
