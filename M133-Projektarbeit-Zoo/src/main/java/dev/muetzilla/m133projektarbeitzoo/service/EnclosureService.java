package dev.muetzilla.m133projektarbeitzoo.service;

import dev.muetzilla.m133projektarbeitzoo.data.DataHandler;
import dev.muetzilla.m133projektarbeitzoo.model.Enclosure;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.validation.constraints.*;
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
     * @return all enclosures saved in the JSON file
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listEnclosures() {
        List<Enclosure> enclosures = DataHandler.getInstance().readAllEnclsoure();
        return Response
                .status(200)
                .entity(enclosures)
                .build();
    }

    /**
     *
     * @param enclosureUUID the UUID of the enclosure that should be read
     * @return UUID which has the UUID passed as parameter
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readEnclosures(
            @NotEmpty
            @Pattern(regexp = "^[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}")
            @QueryParam("uuid") String enclosureUUID
    ) {
        int httpStatus = 200;
        Enclosure enclosure = DataHandler.getInstance().readEnclosureByUUID(enclosureUUID);
        if (enclosure == null) {
            httpStatus = 410;
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
     * @return weather the creation of the zoo was successful or not
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertEnclosure(
            @NotEmpty
            @DecimalMin(value = "1")
            @DecimalMax(value = "500")
            @FormParam("length") Integer length,

            @NotEmpty
            @DecimalMin(value = "1")
            @DecimalMax(value = "500")
            @FormParam("width") Integer width,

            @NotEmpty
            @Size(min=3, max=30)
            @FormParam("kindOfEnclosure") String kindOfEnclosure,

            @NotEmpty
            @Pattern(regexp = "^[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}")
            @FormParam("zooUUID") String zooUUID
    ) {
        Enclosure enclosure = new Enclosure();
        enclosure.setEnclosureUUID(UUID.randomUUID().toString());
        enclosure.setLength(length);
        enclosure.setWidth(width);
        enclosure.setKindOfEnclosure(kindOfEnclosure);
        enclosure.setZooUUID(zooUUID);
        DataHandler.getInstance().insertEnclosure(enclosure);
        return Response
                .status(200)
                .entity("Creation of Gehege successful")
                .build();
    }

    /**
     *
     * @param enclosureUUID the UUID of the enclosure which should be changed
     * @param length the length of the enclosure
     * @param width the width of the enclosure
     * @param kindOfEnclosure the kind of enclosure
     * @param zooUUID the UUID of the zoo the enclosure is in
     * @return weather the update of the enclosure was successful or not
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateEnclosure(
            @NotEmpty
            @Pattern(regexp = "^[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}")
            @FormParam("enclosureUUID") String enclosureUUID,

            @NotEmpty
            @DecimalMin(value = "1")
            @DecimalMax(value = "500")
            @FormParam("length") Integer length,

            @NotEmpty
            @DecimalMin(value = "1")
            @DecimalMax(value = "500")
            @FormParam("width") Integer width,

            @NotEmpty
            @Size(min=3, max=30)
            @FormParam("kindOfEnclosure") String kindOfEnclosure,

            @NotEmpty
            @Pattern(regexp = "^[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}")
            @FormParam("zooUUID") String zooUUID) {
        Enclosure enclosure = DataHandler.getInstance().readEnclosureByUUID(enclosureUUID);
        enclosure.setLength(length);
        enclosure.setWidth(width);
        enclosure.setKindOfEnclosure(kindOfEnclosure);
        enclosure.setZooUUID(zooUUID);

        DataHandler.getInstance().updateEnclosure();
        return Response
                .status(200)
                .entity("Update of enclosure successful")
                .build();
    }

    /**
     *
     * @param enclosureUUID the UUID of the enclosure which should be deleted
     * @return weather the deletion of the enclosure was successful or not
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteEnclosure(
            @QueryParam("enclosureUUID") String enclosureUUID
    ) {
        DataHandler.getInstance().deleteEnclosure(enclosureUUID);
        return Response
                .status(200)
                .entity("Deletion of Gehege successful")
                .build();
    }
}
