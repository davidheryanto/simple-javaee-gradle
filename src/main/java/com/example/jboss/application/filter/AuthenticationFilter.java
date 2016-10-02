package com.example.jboss.application.filter;

import org.json.JSONObject;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

// Reference:
// https://dennis-xlc.gitbooks.io/restful-java-with-jax-rs-2-0-2rd-edition/content/en/part1/chapter12/per_jax_rs_method_bindings.html

// Alternatively, if the filter is going to be applied to ALL requests
// Just annotate the filter implementation with @Provider (don't need to define an interface)

@Provider
@AuthorizationRequired
public class AuthenticationFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authorizationHeader = requestContext.getHeaderString("Authorization");
        if (authorizationHeader == null) {
            requestContext.abortWith(Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity(new JSONObject().put("message", "Please authenticate to access resource").toString())
                    .build()
            );
        }
    }
}
