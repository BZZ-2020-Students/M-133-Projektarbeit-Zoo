package dev.muetzilla.m133projektarbeitzoo.service;

import dev.muetzilla.m133projektarbeitzoo.data.DataHandler;
import dev.muetzilla.m133projektarbeitzoo.model.Gehege;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
@Path("gehege")
public class GehegeService {

    /**
     * reads a list of all books
     * @return  books as JSON
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
     * reads a zoo identified by the uuid
     * @param zooUUID
     * @return zoo
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
}
