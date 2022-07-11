package dev.muetzilla.m133projektarbeitzoo.service;

import dev.muetzilla.m133projektarbeitzoo.data.DataHandler;
import dev.muetzilla.m133projektarbeitzoo.model.Animal;
import dev.muetzilla.m133projektarbeitzoo.util.AES256;
import jakarta.validation.constraints.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
    public Response listAnimal(
            @CookieParam("userRole") String userRole
    ) {
        List<Animal> tierliste = null;
        int httpStatus;
        if(userRole == null || AES256.decrypt(userRole).equals("guest")){
            httpStatus = 401;
        }else {
            tierliste = DataHandler.getInstance().readAllAnimals();
            httpStatus = 200;
        }
        return Response
                .status(httpStatus)
                .entity(tierliste)
                .build();
    }

    /**
     *
     * @param tierUUID the UUID of the animal that should be read
     * @param userRole role of the user
     * @return the animal which has the UUID passed as parameter
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readAnimal(
            @NotEmpty
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String tierUUID,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus = 200;
        Animal animal = null;
        if(userRole == null || AES256.decrypt(userRole).equals("guest")){
            httpStatus = 401;
        }else {
            animal = DataHandler.getInstance().readAnimalByUUID(tierUUID);
            if (animal == null) {
                httpStatus = 410;
            }
        }

        return Response
                .status(httpStatus)
                .entity(animal)
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
     * @param userRole role of the user
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
            @FormParam("birthday") String birthday,

            @NotNull
            @DecimalMin(value = "0")
            @DecimalMax(value = "8")
            @FormParam("amountOfLegs") Integer amountOfLegs,

            @NotEmpty
            @Size(min=1, max=10)
            @FormParam("gender") String gender,

            @NotEmpty
            @Size(min=3, max=100)
            @FormParam("feed")String feed,

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
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("enclosureUUID") String enclosureUUID,

            @CookieParam("userRole") String userRole
    ) {
        int httpStatus;
        String message = "";

        if(userRole == null || AES256.decrypt(userRole).equals("guest") || AES256.decrypt(userRole).equals("user")){
            httpStatus = 401;
            message = "Creation of animal failed, try as a different user again";
        }else {
            Animal animal = new Animal();
            animal.setAnimalUUID(UUID.randomUUID().toString());
            animal.setName(name);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.GERMANY);
            try {
                animal.setBirthday(formatter.parse(birthday));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            animal.setAmountOfLegs(amountOfLegs);
            animal.setGender(gender);
            animal.setFeed(feed);
            animal.setKindOfEnclosure(kindOfEnclosure);
            animal.setBiotop(biotop);
            animal.setKindOfAnimal(kindOfAnimal);
            animal.setEnclosureUUID(enclosureUUID);
            DataHandler.getInstance().insertAnimal(animal);
            httpStatus = 200;
            message = "Creation of animal successful";
        }
        return Response
                .status(httpStatus)
                .entity(message)
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
     * @param userRole role of the user
     * @return a message weather the update of an animal was successful or not
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateAnimal(
            @NotEmpty
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("animalUUID") String animalUUID,

            @NotEmpty
            @Size(min=3, max=20)
            @FormParam("name") String name,

            @NotEmpty
            @Pattern(regexp = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$")
            @FormParam("birthday") String birthday,

            @NotNull
            @DecimalMin(value = "0")
            @DecimalMax(value = "8")
            @FormParam("amountOfLegs") Integer amountOfLegs,

            @NotEmpty
            @Size(min=1, max=10)
            @FormParam("gender") String gender,

            @NotEmpty
            @Size(min=3, max=100)
            @FormParam("feed")String feed,

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
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("enclosureUUID") String enclosureUUID,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus;
        String message = "";
        if(userRole == null || AES256.decrypt(userRole).equals("guest") || AES256.decrypt(userRole).equals("user")){
            httpStatus = 401;
            message = "Update of animal failed, try as a different user again";
        }else {
            Animal animal = DataHandler.getInstance().readAnimalByUUID(animalUUID);
            animal.setName(name);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.GERMANY);
            try {
                animal.setBirthday(formatter.parse(birthday));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            animal.setAmountOfLegs(amountOfLegs);
            animal.setGender(gender);
            animal.setFeed(feed);
            animal.setKindOfEnclosure(kindOfEnclosure);
            animal.setBiotop(biotop);
            animal.setKindOfAnimal(kindOfAnimal);
            animal.setEnclosureUUID(enclosureUUID);
            DataHandler.getInstance().updateAnimal();
            httpStatus = 200;
            message = "Update of animal successful";
        }
                return Response
                .status(httpStatus)
                .entity(message)
                .build();
    }

    /**
     *
     * @param animalUUID the UUID of the animal that should be deleted
     * @param userRole role of the user
     * @return weather the deletion of the animal was successful or not
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteAnimal(
            @NotEmpty
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("animalUUID") String animalUUID,
            @CookieParam("userRole") String userRole

    ) {
        int httpStatus;
        String message = "";

        if(userRole == null || AES256.decrypt(userRole).equals("guest") || AES256.decrypt(userRole).equals("user")){
            httpStatus = 401;
            message = "Deletion of animal failed, try as a different user again";
        }else {
            DataHandler.getInstance().deleteAnimal(animalUUID);
            httpStatus = 200;
            message = "Deletion of animal successful";
        }
        return Response
                .status(httpStatus)
                .entity(message)
                .build();
    }
}
