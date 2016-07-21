import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/fruit")
public class FruitService {
    @GET
    public Response get() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fruit", "apple");
        jsonObject.put("amount", 5);
        jsonObject.put("isEdible", true);
        return Response.ok(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE).build();
    }
}
