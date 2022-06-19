package dev.muetzilla.m133projektarbeitzoo.service;

import dev.muetzilla.m133projektarbeitzoo.data.DataHandler;
import dev.muetzilla.m133projektarbeitzoo.model.Animal;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Author: Moritz
 * @Date: 2022-05-16
 * @Since 1.0.0-SNAPSHOT
 *
 * @Description: Service for the animal
 *
 */
@Path("/animal")
public class AnimalService {
    /**
     * @return all animals which are stored in the JSON file
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
     * @param tierUUID the UUID of the animal that should be read
     * @return the animal which has the UUID passed as parameter
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readAnimal(
            @NotEmpty
            @Pattern(regexp = "^[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}")
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
     *
     * @param name the name of the animal
     * @param birthday the birthday of the animal
     * @param amountOfLegs the amount of legs an animal has (must be even since there is no animal with an odd number of legs)
     * @param gender the gender of the animal
     * @param feed the feed this animal gets
     * @param kindOfEnclosure the kind of enclosure this animal lives in
     * @param biotop the natural biotop the animals would live in
     * @param kindOfAnimal the kind of this animal
     * @param enclosureUUID the UUID in which this animal lives
     * @return a message weather the creation of a new animal was successful or not
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertAnimal(
            @NotEmpty
            @Size(min=3, max=20)
            @FormParam("name") String name,

            @NotEmpty
            @Pattern(regexp = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$")
            @FormParam("birthday") Date birthday,

            @NotEmpty
            @DecimalMin(value = "0")
            @DecimalMax(value = "8")
            @Pattern(regexp = "^-?\\d*[02468]$")
            @FormParam("amountOfLegs") Integer amountOfLegs,

            @NotEmpty
            @Size(min=1, max=10)
            @FormParam("gender") String gender,

            @NotEmpty
            @FormParam("feed")ArrayList<String> feed,

            @NotEmpty
            @Size(min=5, max=25)
            @FormParam("kindOfEnclosure")String kindOfEnclosure,

            @NotEmpty
            @Size(min=3, max=20)
            @FormParam("biotop")String biotop,

            @NotEmpty
            @Size(min=3, max=30)
            @FormParam("kindOfAnimal")String kindOfAnimal,

            @NotEmpty
            @Pattern(regexp = "^[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}")
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
     * @param animalUUID the UUID the animal that should be changed
     * @param name the name of the animal
     * @param birthday the birthday of the animal
     * @param amountOfLegs the amount of legs an animal has (must be even since there is no animal with an odd number of legs)
     * @param gender the gender of the animal
     * @param feed the feed this animal gets
     * @param kindOfEnclosure the kind of enclosure this animal lives in
     * @param biotop the natural biotop the animals would live in
     * @param kindOfAnimal the kind of this animal
     * @param enclosureUUID the UUID in which this animal lives
     * @return a message weather the update of an animal was successful or not
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateAnimal(
            @NotEmpty
            @Pattern(regexp = "^[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}")
            @FormParam("animalUUID") String animalUUID,

            @NotEmpty
            @Size(min=3, max=20)
            @FormParam("name") String name,

            @NotEmpty
            @Pattern(regexp = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$")
            @FormParam("birthday") Date birthday,

            @NotEmpty
            @DecimalMin(value = "0")
            @DecimalMax(value = "8")
            @Pattern(regexp = "^-?\\d*[02468]$")
            @FormParam("amountOfLegs") Integer amountOfLegs,

            @NotEmpty
            @Size(min=1, max=10)
            @FormParam("gender") String gender,

            @NotEmpty
            @FormParam("feed")ArrayList<String> feed,

            @NotEmpty
            @Size(min=5, max=25)
            @FormParam("kindOfEnclosure")String kindOfEnclosure,

            @NotEmpty
            @Size(min=3, max=20)
            @FormParam("biotop")String biotop,

            @NotEmpty
            @Size(min=3, max=30)
            @FormParam("kindOfAnimal")String kindOfAnimal,

            @NotEmpty
            @Pattern(regexp = "^[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}")
            @FormParam("enclosureUUID") String enclosureUUID)
            {
                Animal animal = DataHandler.getInstance().readAnimalByUUID(animalUUID);
                animal.setName(name);
                animal.setBirthday(birthday);
                animal.setAmountOfLegs(amountOfLegs);
                animal.setGender(gender);
                animal.setFeed(feed);
                animal.setKindOfEnclosure(kindOfEnclosure);
                animal.setBiotop(biotop);
                animal.setKindOfAnimal(kindOfAnimal);
                animal.setEnclosureUUID(enclosureUUID);
                DataHandler.getInstance().updateAnimal();

                return Response
                .status(200)
                .entity("Update of animal successful")
                .build();
    }

    /**
     *
     * @param animalUUID the UUID of the animal that should be deleted
     * @return weather the deletion of the animal was successful or not
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteAnimal(
            @NotEmpty
            @Pattern(regexp = "^[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}")
            @QueryParam("animalUUID") String animalUUID
    ) {
        DataHandler.getInstance().deleteAnimal(animalUUID);
        return Response
                .status(200)
                .entity("Deletion of animal successful")
                .build();
    }
}
