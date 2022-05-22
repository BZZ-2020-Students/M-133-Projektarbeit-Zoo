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

@Path("/tier")
public class TierService {
    /**
     * reads a list of all books
     * @return  books as JSON
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
     * reads a zoo identified by the uuid
     * @param zooUUID
     * @return zoo
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
