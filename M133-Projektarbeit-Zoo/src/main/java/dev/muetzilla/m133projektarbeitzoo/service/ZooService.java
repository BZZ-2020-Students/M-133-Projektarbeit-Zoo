package dev.muetzilla.m133projektarbeitzoo.service;

import dev.muetzilla.m133projektarbeitzoo.data.DataHandler;
import dev.muetzilla.m133projektarbeitzoo.model.Zoo;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;


@Path("zoo")
public class ZooService {

    /**
     * reads a list of all books
     * @return  books as JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listZoos() {
        List<Zoo> zooList = DataHandler.getInstance().readAllZoos();
        return Response
                .status(200)
                .entity(zooList)
                .build();
    }

    /**
     * reads a zoo identified by the uuid
     * @param zooUUID
     * @return zoo
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readZoo(
            @QueryParam("uuid") String zooUUID
    ) {
        int httpStatus = 200;
        Zoo zoo = DataHandler.getInstance().readZooByUUID(zooUUID);
        if (zoo == null) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(zoo)
                .build();
    }
}