package ch.muetzilla.m133projektarbeitzoo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
@Path("/hello-world")
public class HelloResource {
    @GET
    @Produces("application/json")
    public String hello() {
        return "{\"name\":\"John\", \"age\": \"30\"}";
    }
}