package dev.muetzilla.m133projektarbeitzoo.data;


import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import dev.muetzilla.m133projektarbeitzoo.model.Enclosure;
import dev.muetzilla.m133projektarbeitzoo.model.Animal;
import dev.muetzilla.m133projektarbeitzoo.model.User;
import dev.muetzilla.m133projektarbeitzoo.model.Zoo;
import dev.muetzilla.m133projektarbeitzoo.service.Config;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Moritz
 * @Date: 2022-05-16
 * @Since 1.0.0-SNAPSHOT
 *
 * @Description: Reads the data from the JSON files and prepares them for the service
 *
 */
public class DataHandler {
    private static DataHandler instance = null;
    private List<Zoo> zooList;
    private List<Enclosure> enclosureList;
    private List<Animal> animalList;
    private List<User> userList;


    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setZooList(new ArrayList<>());
        readZooJSON();
        setEnclosureList(new ArrayList<>());
        readGehegeJSON();
        setAnimalList(new ArrayList<>());
        readAnimalJSON();
        setUserList(new ArrayList<>());
        readUserJSON();
    }

    /**
     * @return the instance of the DataHandler
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }


    /**
     * @return a list of all zpps
     */
    public List<Zoo> readAllZoos() {
        return getZooList();
    }


    /**
     * @return the list of all enclosures
     */
    public List<Enclosure> readAllEnclsoure() {
        return getEnclosureList();
    }

    /**
     * @return the list of all animals
     */
    public List<Animal> readAllAnimals() {
        return getAnimalList();
    }



    /**
     * @param zooUUID the UUID of the zoo we want to read
     * @return the zoo with this UUID (null=not found)
     */
    public Zoo readZooByUUID(String zooUUID) {
        Zoo zoo = null;
        for (Zoo entry : getZooList()) {
            if (entry.getZooUUID().equals(zooUUID)) {
                zoo = entry;
            }
        }
        return zoo;
    }

    /**
     * Adds a new Zoo to the JSON file
     * @param zoo the new zoo
     */
    public void insertZoo(Zoo zoo) {
        getZooList().add(zoo);
        writeZooJSON();
    }

    /**
     * updated the list of all Zoos and writes them to the JSON file
     */
    public void upadteZoo() {
        writeZooJSON();
    }

    /**
     * @param zooUUID  the UUID of the zoo that should be deleted
     * @return  success=true/false
     */
    public boolean deleteZoo(String zooUUID) {
        Zoo zoo = readZooByUUID(zooUUID);
        if (zoo != null) {
            getZooList().remove(zoo);
            writeZooJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @return a list of all the zoos
     */

    private List<Zoo> getZooList() {
        return zooList;
    }

    /***
     * @param zooList sets the list of all the zoos needed
     */

    private void setZooList(List<Zoo> zooList) {
        this.zooList = zooList;
    }

    /**
     * Reads the Zoos from the JSON file
     */
    private void readZooJSON() {
        try {
            String path = Config.getProperty("zooJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Zoo[] zoos = objectMapper.readValue(jsonData, Zoo[].class);
            for (Zoo zoo : zoos) {
                getZooList().add(zoo);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Writes all the zoos in the list into the JSON file
     */
    private void writeZooJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String bookPath = Config.getProperty("zooJSON");
        try {
            fileOutputStream = new FileOutputStream(bookPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getZooList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * @param enclosureUUID the UUID of the enclosure that should be read
     * @return the enclosure with the UUID passed as parameter (null=not found)
     */
    public Enclosure readEnclosureByUUID(String enclosureUUID) {
        Enclosure gehege = null;
        for (Enclosure entry : getEnclosureList()) {
            if (entry.getEnclosureUUID().equals(enclosureUUID)) {
                gehege = entry;
            }
        }
        return gehege;
    }

    /**
     * @param enclosure adds a new enclosure to the list and writes all enclosured to the JSON file
     */
    public void insertEnclosure(Enclosure enclosure) {
        getEnclosureList().add(enclosure);
        writeGehegeJSON();
    }

    /**
     * Updates the list of enclosures
     */
    public void updateEnclosure() {
        writeGehegeJSON();
    }

    /**
     * @param enclosureUUID the UUID of the enclsoure which should be deleted
     * @return success=true/false
     */
    public boolean deleteEnclosure(String enclosureUUID) {
        Enclosure enclosure = readEnclosureByUUID(enclosureUUID);
        if (enclosure != null) {
            getEnclosureList().remove(enclosure);
            writeGehegeJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Reads all the enclosured from the JSON file
     */
    private void readGehegeJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("enclosureJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Enclosure[] enclosureArray = objectMapper.readValue(jsonData, Enclosure[].class);
            for (Enclosure enclosure : enclosureArray) {
                getEnclosureList().add(enclosure);
                System.out.println(enclosure);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Writes all the enclosures to the JSON file
     */
    private void writeGehegeJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String bookPath = Config.getProperty("enclosureUUID");
        try {
            fileOutputStream = new FileOutputStream(bookPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getEnclosureList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @return the list of all the enclosures
     */

    private List<Enclosure> getEnclosureList() {
        return enclosureList;
    }

    /**
     * @param enclosureList sets the list of all enclosures
     */

    private void setEnclosureList(List<Enclosure> enclosureList) {
        this.enclosureList = enclosureList;
    }


    /**
     * @param animalUUID the UUID of the animal that should be read
     * @return the animal with the UUID passed as parameter (null=not found)
     */
    public Animal readAnimalByUUID(String animalUUID) {
        Animal animal = null;
        for (Animal entry : getAnimalList()) {
            if (entry.getAnimalUUID().equals(animalUUID)) {
                animal = entry;
            }
        }
        return animal;
    }

    /**
     * Updates the list of all animals
     */
    public void updateAnimal() {
        writeAnimalJSON();
    }

    /**
     * @param animalUUID the UUID of the animal which should be deleted
     * @return  success=true/false
     */
    public boolean deleteAnimal(String animalUUID) {
        Animal animal = readAnimalByUUID(animalUUID);
        if (animal != null) {
            getEnclosureList().remove(animal);
            writeAnimalJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Reads all animals from the JSON file
     */
    private void readAnimalJSON() {
        try {
            String path = Config.getProperty("animalJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Animal[] animals = objectMapper.readValue(jsonData, Animal[].class);
            for (Animal animal : animals) {
                getAnimalList().add(animal);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Writes all animals into the JSON file
     */
    private void writeAnimalJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String bookPath = Config.getProperty("animalJSON");
        try {
            fileOutputStream = new FileOutputStream(bookPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getAnimalList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Adds a new animal
     * @param animal the new animal
     */
    public void insertAnimal(Animal animal) {
        getAnimalList().add(animal);
        writeAnimalJSON();
    }

    /**
     * @return a list of all animals
     */

    private List<Animal> getAnimalList() {
        return animalList;
    }

    /**
     * @param animalList the list of all animals
     */

    private void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }


    /**
     * reads the users from the JSON-file
     */
    private void readUserJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("userJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            User[] users = objectMapper.readValue(jsonData, User[].class);
            for (User user : users) {
                getUserList().add(user);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String readUserRole(String username, String password) {
        for (User user : getUserList()) {
            if (user.getUsername().equals(username) &&
                    user.getPassword().equals(password)) {
                return user.getUsername();
            }

        }
        return "guest";
    }


    /**
     * gets userList
     *
     * @return value of userList
     */

    public List<User> getUserList() {
        return userList;
    }
    public void setUserList(List<User> userlist){
        this.userList = userlist;
    }

}