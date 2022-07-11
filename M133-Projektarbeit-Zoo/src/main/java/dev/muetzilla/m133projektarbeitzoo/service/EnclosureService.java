package dev.muetzilla.m133projektarbeitzoo.service;

import dev.muetzilla.m133projektarbeitzoo.data.DataHandler;
import dev.muetzilla.m133projektarbeitzoo.model.Enclosure;
import dev.muetzilla.m133projektarbeitzoo.util.AES256;
import jakarta.validation.constraints.*;
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
 * @Description: Service for an enclosure
 *
 */
@Path("enclosure")
public class EnclosureService {

    /**
     * @param userRole role of the user
     * @return all enclosures saved in the JSON file
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listEnclosures(
            @CookieParam("userRole") String userRole

    ) {
        List<Enclosure> enclosures = null;
        int httpStatus;
        if(userRole == null || AES256.decrypt(userRole).equals("guest") ){
            httpStatus = 401;
        }else {
            httpStatus = 200;
            enclosures = DataHandler.getInstance().readAllEnclsoure();
        }
        return Response
                .status(httpStatus)
                .entity(enclosures)
                .build();
    }

    /**
     *
     * @param enclosureUUID the UUID of the enclosure that should be read
     * @param userRole role of the user
     * @return UUID which has the UUID passed as parameter
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readEnclosures(
            @NotEmpty
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String enclosureUUID,
            @CookieParam("userRole") String userRole

    ) {
        int httpStatus = 200;
        Enclosure enclosure = null;
        if(userRole == null || AES256.decrypt(userRole).equals("guest")){
            httpStatus = 401;

        }else {
            enclosure = DataHandler.getInstance().readEnclosureByUUID(enclosureUUID);
            if (enclosure == null) {
                httpStatus = 410;
            }
        }
        return Response
                .status(httpStatus)
                .entity(enclosure)
                .build();
    }

    /**
     *
     * @param length the length of the enclosure
     * @param width the width of the enclosure
     * @param kindOfEnclosure the kind of the enclosure
     * @param zooUUID the UUID of the zoo in which the enclosure is
     * @param userRole role of the user
     * @return weather the creation of the zoo was successful or not
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertEnclosure(
            @NotNull
            @DecimalMin(value = "1")
            @DecimalMax(value = "500")
            @FormParam("length") Integer length,

            @NotNull
            @DecimalMin(value = "1")
            @DecimalMax(value = "500")
            @FormParam("width") Integer width,

            @NotEmpty
            @Size(min=3, max=30)
            @FormParam("kindOfEnclosure") String kindOfEnclosure,

            @NotEmpty
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("zooUUID") String zooUUID,
            @CookieParam("userRole") String userRole

    ) {
        int httpStatus;
        String message = "";
        if(userRole == null || AES256.decrypt(userRole).equals("guest") || AES256.decrypt(userRole).equals("user")){
            httpStatus = 401;
            message = "Creation of enclosure failed, try as a different user again";
        }else {
            Enclosure enclosure = new Enclosure();
            enclosure.setEnclosureUUID(UUID.randomUUID().toString());
            enclosure.setLength(length);
            enclosure.setWidth(width);
            enclosure.setKindOfEnclosure(kindOfEnclosure);
            enclosure.setZooUUID(zooUUID);
            DataHandler.getInstance().insertEnclosure(enclosure);
            httpStatus = 200;
            message = "Creation of enclosure successful";
        }
        return Response
                .status(httpStatus)
                .entity(message)
                .build();
    }

    /**
     *
     * @param enclosureUUID the UUID of the enclosure which should be changed
     * @param length the length of the enclosure
     * @param width the width of the enclosure
     * @param kindOfEnclosure the kind of enclosure
     * @param zooUUID the UUID of the zoo the enclosure is in
     * @param userRole role of the user
     * @return weather the update of the enclosure was successful or not
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateEnclosure(
            @NotEmpty
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("enclosureUUID") String enclosureUUID,

            @NotNull
            @DecimalMin(value = "1")
            @DecimalMax(value = "500")
            @FormParam("length") Integer length,

            @NotNull
            @DecimalMin(value = "1")
            @DecimalMax(value = "500")
            @FormParam("width") Integer width,

            @NotEmpty
            @Size(min=3, max=30)
            @FormParam("kindOfEnclosure") String kindOfEnclosure,

            @NotEmpty
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("zooUUID") String zooUUID,
            @CookieParam("userRole") String userRole

    ) {
        int httpStatus;
        String message = "";

        if(userRole == null || AES256.decrypt(userRole).equals("guest") || AES256.decrypt(userRole).equals("user")){
            httpStatus = 401;
            message = "Update of enclosure failed, try as a different user again";
        }else {
            Enclosure enclosure = DataHandler.getInstance().readEnclosureByUUID(enclosureUUID);
            enclosure.setLength(length);
            enclosure.setWidth(width);
            enclosure.setKindOfEnclosure(kindOfEnclosure);
            enclosure.setZooUUID(zooUUID);
            DataHandler.getInstance().updateEnclosure();
            httpStatus = 200;
            message = "Update of enclosure successful";
        }

        return Response
                .status(httpStatus)
                .entity(message)
                .build();
    }

    /**
     *
     * @param enclosureUUID the UUID of the enclosure which should be deleted
     * @param userRole role of the user
     * @return weather the deletion of the enclosure was successful or not
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteEnclosure(
            @NotEmpty
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("enclosureUUID") String enclosureUUID,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus;
        String message = "";
        if(userRole == null || AES256.decrypt(userRole).equals("guest") || AES256.decrypt(userRole).equals("user")){
            httpStatus = 401;
            message = "Deletion of enclosure failed, try as a different user again";
        }else {
            DataHandler.getInstance().deleteEnclosure(enclosureUUID);
            httpStatus = 200;
            message = "Deletion of Gehege successful";
        }
        return Response
                .status(httpStatus)
                .entity(message)
                .build();
    }
}
