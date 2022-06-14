package dev.muetzilla.m133projektarbeitzoo.service;

import dev.muetzilla.m133projektarbeitzoo.data.DataHandler;
import dev.muetzilla.m133projektarbeitzoo.model.Gehege;
import dev.muetzilla.m133projektarbeitzoo.model.Animal;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Author: Moritz
 * @Date: 2022-05-16
 * @Since 1.0.0-SNAPSHOT
 *
 * @Description: Service für das Tier
 *
 */
@Path("/tier")
public class AnimalService {
    /**
     * @return alle Tiere welche im JSON gespeichert werden
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAnimal() {
        List<Animal> tierliste = DataHandler.getInstance().readAllAnimals();
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
    public Response readAnimal(
            @QueryParam("uuid") String tierUUID
    ) {
        int httpStatus = 200;
        Animal tier = DataHandler.getInstance().readAnimalByUUID(tierUUID);
        if (tier == null) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(tier)
                .build();
    }
    /**
     * @return alle Gehege welche im JSON gespeichert werden
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertAnimal(
            @FormParam("name") String name,
            @FormParam("birthday") Date birthday,
            @FormParam("amountOfLegs") Integer amountOfLegs,
            @FormParam("gender") String gender,
            @FormParam("feed")ArrayList<String> feed,
            @FormParam("kindOfEnclosure")String kindOfEnclosure,
            @FormParam("biotop")String biotop,
            @FormParam("kindOfAnimal")String kindOfAnimal,
            @FormParam("enclosureUUID") String enclosureUUID
    ) {
        Animal animal = new Animal();
        animal.setAnimalUUID(UUID.randomUUID().toString());
        animal.setName(name);
        animal.setBirthday(birthday);
        animal.setAmountOfLegs(amountOfLegs);
        animal.setGender(gender);
        animal.setFeed(feed);
        animal.setKindOfEnclosure(kindOfEnclosure);
        animal.setBiotop(biotop);
        animal.setKindOfAnimal(kindOfAnimal);
        animal.setEnclosureUUID(enclosureUUID);
        DataHandler.getInstance().insertAnimal(animal);
        return Response
                .status(200)
                .entity("Creation of animal successful")
                .build();
    }
    /**
     * @return alle Gehege welche im JSON gespeichert werden
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateAnimal() {
        List<Gehege> gehegeList = DataHandler.getInstance().readAllGehege();
        return Response
                .status(200)
                .entity(gehegeList)
                .build();
    }
    /**
     * @return alle Gehege welche im JSON gespeichert werden
     */
    @GET
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteAnimal(
            @QueryParam("gehegeUuid") String gehegeUUID
    ) {
        DataHandler.getInstance().deleteGehege(gehegeUUID);
        return Response
                .status(200)
                .entity("Deletion of Gehege successful")
                .build();
    }
}
