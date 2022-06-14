package dev.muetzilla.m133projektarbeitzoo.service;

import dev.muetzilla.m133projektarbeitzoo.data.DataHandler;
import dev.muetzilla.m133projektarbeitzoo.model.Gehege;
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
 * @Description: Service für das Gehege
 *
 */
@Path("gehege")
public class GehegeService {

    /**
     * @return alle Gehege welche im JSON gespeichert werden
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listGeheges() {
        List<Gehege> gehegeList = DataHandler.getInstance().readAllGehege();
        return Response
                .status(200)
                .entity(gehegeList)
                .build();
    }

    /**
     *
     * @param gehegeUUID UUID des Geheges welches geladen werden soll
     * @return Gehege welches mit der gegebenen UUID übereinstimmt
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readGehege(
            @QueryParam("uuid") String gehegeUUID
    ) {
        int httpStatus = 200;
        Gehege gehege = DataHandler.getInstance().readGehegeByUUID(gehegeUUID);
        if (gehege == null) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(gehege)
                .build();
    }
    /**
     * @return alle Gehege welche im JSON gespeichert werden
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertGehege(
            @FormParam("laenge") Integer laenge,
            @FormParam("breite") Integer breite,
            @FormParam("gehegeArt") String gehegeArt,
            @FormParam("zooUUID") String zooUUID
    ) {
        Gehege gehege = new Gehege();
        gehege.setGehegeUUID(UUID.randomUUID().toString());
        gehege.setLaenge(laenge);
        gehege.setBreite(breite);
        gehege.setGehegeArt(gehegeArt);
        gehege.setZooUUID(zooUUID);
        DataHandler.getInstance().insertGehege(gehege);
        return Response
                .status(200)
                .entity("Creation of Gehege successful")
                .build();
    }
    /**
     * @return alle Gehege welche im JSON gespeichert werden
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateGehege(  @FormParam("enclosureUUID") String enclosureUUID,
                                   @FormParam("laenge") Integer laenge,
                                   @FormParam("breite") Integer breite,
                                   @FormParam("gehegeArt") String gehegeArt,
                                   @FormParam("zooUUID") String zooUUID) {
        Gehege enclosure = DataHandler.getInstance().readGehegeByUUID(enclosureUUID);
        enclosure.setLaenge(laenge);
        enclosure.setBreite(breite);
        enclosure.setGehegeArt(gehegeArt);
        enclosure.setZooUUID(zooUUID);

        DataHandler.getInstance().updateGehege();
        return Response
                .status(200)
                .entity("Update of enclosure successful")
                .build();
    }
    /**
     * @return alle Gehege welche im JSON gespeichert werden
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteGehege(
            @QueryParam("enclosureUUID") String enclosureUUID
    ) {
        DataHandler.getInstance().deleteGehege(enclosureUUID);
        return Response
                .status(200)
                .entity("Deletion of Gehege successful")
                .build();
    }
}
