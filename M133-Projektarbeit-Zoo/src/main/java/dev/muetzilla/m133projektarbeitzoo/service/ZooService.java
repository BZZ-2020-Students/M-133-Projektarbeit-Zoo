package dev.muetzilla.m133projektarbeitzoo.service;

import dev.muetzilla.m133projektarbeitzoo.data.DataHandler;
import dev.muetzilla.m133projektarbeitzoo.model.Zoo;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

/**
 * @Author: Moritz
 * @Date: 2022-05-16
 * @Since 1.0.0-SNAPSHOT
 *
 * @Description: Service für einen Zoo
 *
 */
@Path("zoo")
public class ZooService {

    /**
     * @return Eine Liste aller Zoos als JSON
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
     * @param zooUUID UUID des Zoos
     * @return Zoo, welcher mit der übergebenen UUID übereinstimmt
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

    /**
     *
     * @param zooUUID UUID des Zoos
     * @return
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteZoo(
            @QueryParam("zooUUID") String zooUUID
    ) {
        int httpStatus = 200;
        DataHandler.getInstance().deleteZoo(zooUUID);
        return Response
                .status(httpStatus)
                .entity("Deletion of zoo successful")
                .build();
    }

    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateZoo(
            @FormParam("zooUUID") String zooUUID,
            @FormParam("zooName") String zooName) {
        int httpStatus = 200;
        Zoo zoo = DataHandler.getInstance().readZooByUUID(zooUUID);
        zoo.setZooName(zooName);

        DataHandler.getInstance().upadteZoo();
        return Response
                .status(httpStatus)
                .entity("Update of zoo successful")
                .build();
    }

    /**
     *
     * @return
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)

    public Response insertZoo(
            @FormParam("zooName") String zooName    ) {
        Zoo zoo = new Zoo();
        zoo.setZooUUID(UUID.randomUUID().toString());
        zoo.setZooName(zooName);

        DataHandler.getInstance().insertZoo(zoo);

        int httpStatus = 200;

        return Response
                .status(httpStatus)
                .entity("Creation of Zoo successful")
                .build();
    }
}