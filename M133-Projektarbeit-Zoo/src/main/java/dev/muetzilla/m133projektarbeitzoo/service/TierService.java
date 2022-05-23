package dev.muetzilla.m133projektarbeitzoo.service;

import dev.muetzilla.m133projektarbeitzoo.data.DataHandler;
import dev.muetzilla.m133projektarbeitzoo.model.Gehege;
import dev.muetzilla.m133projektarbeitzoo.model.Tier;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * @Author: Moritz
 * @Date: 2022-05-16
 * @Since 1.0.0-SNAPSHOT
 *
 * @Description: Service für das Tier
 *
 */
@Path("/tier")
public class TierService {
    /**
     * @return alle Tiere welche im JSON gespeichert werden
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listTiere() {
        List<Tier> tierliste = DataHandler.getInstance().readAllTiere();
        return Response
                .status(200)
                .entity(tierliste)
                .build();
    }

    /**
     *
     * @param tierUUID uuid des Tieres
     * @return das Tier, welches mit der gesuchten UUID
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readTier(
            @QueryParam("uuid") String tierUUID
    ) {
        int httpStatus = 200;
        Tier tier = DataHandler.getInstance().readTierByUUID(tierUUID);
        if (tier == null) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(tier)
                .build();
    }
}
