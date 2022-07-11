package dev.muetzilla.m133projektarbeitzoo.service;

import dev.muetzilla.m133projektarbeitzoo.data.DataHandler;
import dev.muetzilla.m133projektarbeitzoo.model.Zoo;
import dev.muetzilla.m133projektarbeitzoo.util.AES256;
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
     * @param userRole role of the user
     * @return a list of all the zoos in the JSON file
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listZoos(
            @CookieParam("userRole") String userRole
    ) {
        List<Zoo> zooList = null;
        int httpStatus;
        if(userRole == null || AES256.decrypt(userRole).equals("guest")){
            httpStatus = 401;
        }else{
            httpStatus = 200;
            zooList = DataHandler.getInstance().readAllZoos();
        }
        return Response
                .status(httpStatus)
                .entity(zooList)
                .build();
    }

    /**
     * @param zooUUID the UUID of the zoo
     * @param userRole role of the user
     * @return the zoo with the UUID passed as parameter
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readZoo(
            @NotEmpty
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String zooUUID,
            @CookieParam("userRole") String userRole

    ) {
        int httpStatus = 200;
        Zoo zoo = null;
        if(userRole == null || AES256.decrypt(userRole).equals("guest")){
            httpStatus = 401;
        }else {
            zoo = DataHandler.getInstance().readZooByUUID(zooUUID);
            if (zoo == null) {
                httpStatus = 410;
            }
        }
        return Response
                .status(httpStatus)
                .entity(zoo)
                .build();
    }

    /**
     *
     * @param zooUUID the UUID of the zoo that should be deleted
     * @param userRole role of the user
     * @return weather the deletion of the zoo was successful or not
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteZoo(
            @NotEmpty
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("zooUUID") String zooUUID,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus = 200;
        String message = "";
        if(userRole == null || AES256.decrypt(userRole).equals("guest") ||  AES256.decrypt(userRole).equals("user")){
            httpStatus = 401;
            message = "Deletion of zoo failed, try again as a different user";
        }else {
            DataHandler.getInstance().deleteZoo(zooUUID);
            message = "Deletion of zoo successful";
        }
        return Response
                .status(httpStatus)
                .entity(message)
                .build();
    }

    /**
     *
     * @param zooUUID the UUID of the zoo that should be updated
     * @param zooName the name of the zoo
     * @param userRole role of the user
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
            @FormParam("zooName") String zooName,
            @CookieParam("userRole") String userRole
    )
    {
        int httpStatus = 200;
        String message = "";
        if(userRole == null || AES256.decrypt(userRole).equals("guest")  || AES256.decrypt(userRole).equals("user")){
            httpStatus = 401;
            message = "Update of zoo failed, try again as a different user";
        }else {
            Zoo zoo = DataHandler.getInstance().readZooByUUID(zooUUID);
            zoo.setZooName(zooName);
            DataHandler.getInstance().upadteZoo();
            message = "Update of zoo successful";
        }
        return Response
                .status(httpStatus)
                .entity(message)
                .build();
    }

    /**
     *
     * @param zooName the name of the zoo
     * @param userRole role of the user
     * @return weather the creation of the zoo was successful or not
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)

    public Response insertZoo(
            @NotEmpty
            @Size(min=5, max=30)
            @FormParam("zooName") String zooName,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus = 200;
        String message = "";
        if(userRole == null || AES256.decrypt(userRole).equals("guest") || AES256.decrypt(userRole).equals("user")){
            httpStatus = 401;
            message = "Creation of zoo failed, try again with a different user";
        }else {
            Zoo zoo = new Zoo();
            zoo.setZooUUID(UUID.randomUUID().toString());
            zoo.setZooName(zooName);
            DataHandler.getInstance().insertZoo(zoo);
            message = "Creation of Zoo successful";
        }
        return Response
                .status(httpStatus)
                .entity(message)
                .build();
    }
}