package com.example.jboss.application.service;

import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Path("/hello")
public class HelloService {
    @GET
    public Response get(@Context HttpHeaders headers) throws UnsupportedEncodingException {
        MultivaluedMap requestHeaders = headers.getRequestHeaders();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isAuthorized", false);

        if (requestHeaders.containsKey("Authorization")) {
            String expectedValue = "Basic " + Base64.getEncoder().encodeToString("user:pass".getBytes("UTF-8"));
            String receivedValue = headers.getHeaderString("Authorization");
            if (expectedValue.equals(receivedValue)) {
                jsonObject.put("isAuthorized", true);
            }
        }

        jsonObject.put("message", "Hello World");
        return Response.ok(jsonObject.toString()).build();
    }
}
