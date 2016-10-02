package com.example.jboss.application.service;

import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/fruits")
@Produces(MediaType.APPLICATION_JSON)
// Uncomment below to enable Basic authorization support
// @AuthorizationRequired
public class FruitService {
    @GET
    public Response getFruits(@QueryParam("ids") String ids) {
        List<Integer> fruitIds = new ArrayList<>();
        String comment = null;

        if (ids != null) {
            for (String token : ids.split(",")) {
                try {
                    fruitIds.add(Integer.valueOf(token.trim()));
                } catch (NumberFormatException e) {
                    return Response.status(Response.Status.BAD_REQUEST)
                            .entity(new JSONObject().put("message", token + " is not a number").toString())
                            .build();
                }
            }
        } else {
            int fruitCount = 3;
            for (int i = 0; i < fruitCount; i++) {
                fruitIds.add((int) Math.round(Math.random() * 10));
            }
            comment = "Ids are randomly generated";
        }

        List<JSONObject> fruits = new ArrayList<>();
        for (Integer fruitId : fruitIds) {
            JSONObject fruit = new JSONObject();
            fruit.put("id", fruitId);
            fruit.put("amount", Math.round(Math.random() * 5));
            fruit.put("isEdible", true);
            fruits.add(fruit);
        }

        JSONObject output = new JSONObject()
                .put("fruits", fruits)
                .put("comment", comment == null ? JSONObject.NULL : comment);
        return Response.ok(output.toString()).build();
    }

    @GET
    @Path("{id}")
    public Response getFruitById(@PathParam("id") int id) {
        JSONObject fruit = new JSONObject();
        fruit.put("id", id);
        fruit.put("amount", Math.round(Math.random() * 5));
        fruit.put("isEdible", true);
        return Response.ok(fruit.toString()).build();
    }
}
