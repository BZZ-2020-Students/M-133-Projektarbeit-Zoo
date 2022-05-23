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
}