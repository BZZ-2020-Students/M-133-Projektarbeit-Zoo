package dev.muetzilla.m133projektarbeitzoo.service;

import dev.muetzilla.m133projektarbeitzoo.data.DataHandler;
import dev.muetzilla.m133projektarbeitzoo.model.Zoo;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
 * @Description: Service for a zoo
 *
 */
@Path("zoo")
public class ZooService {

    /**
     * @return a list of all the zoos in the JSON file
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
     * @param zooUUID the UUID of the zoo
     * @return the zoo with the UUID passed as parameter
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readZoo(
            @NotEmpty
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
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
     * @param zooUUID the UUID of the zoo that should be deleted
     * @return weather the deletion of the zoo was successful or not
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteZoo(
            @NotEmpty
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("zooUUID") String zooUUID
    ) {
        int httpStatus = 200;
        DataHandler.getInstance().deleteZoo(zooUUID);
        return Response
                .status(httpStatus)
                .entity("Deletion of zoo successful")
                .build();
    }

    /**
     *
     * @param zooUUID the UUID of the zoo that should be updated
     * @param zooName the name of the zoo
     * @return weather the update of the zoo was successful or not
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateZoo(
            @NotEmpty
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("zooUUID") String zooUUID,

            @NotEmpty
            @Size(min=3, max=30)
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
     * @param zooName the name of the zoo
     * @return weather the creation of the zoo was successful or not
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)

    public Response insertZoo(
            @NotEmpty
            @Size(min=5, max=30)
            @FormParam("zooName") String zooName) {
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