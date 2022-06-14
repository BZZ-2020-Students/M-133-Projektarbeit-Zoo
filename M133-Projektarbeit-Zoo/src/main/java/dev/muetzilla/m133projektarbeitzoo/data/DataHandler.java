package dev.muetzilla.m133projektarbeitzoo.data;


import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import dev.muetzilla.m133projektarbeitzoo.model.Gehege;
import dev.muetzilla.m133projektarbeitzoo.model.Animal;
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
 * @Description: Liest Daten aus der JSON Datei aus und bereitet diese für die Service vor.
 *
 */
public class DataHandler {
    private static DataHandler instance = null;
    private List<Zoo> zooList;
    private List<Gehege> gehegeList;
    private List<Animal> animalList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setZooList(new ArrayList<>());
        readZooJSON();
        setGehegeList(new ArrayList<>());
        readGehegeJSON();
        setAnimalList(new ArrayList<>());
        readAnimalJSON();
    }

    /**
     * @return Instanz des DataHandlers
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }


    /**
     * @return Liste von Zoos
     */
    public List<Zoo> readAllZoos() {
        return getZooList();
    }


    /**
     * @return Liste von Gehegen
     */
    public List<Gehege> readAllGehege() {
        return getGehegeList();
    }

    /**
     * @return Liste von Tieren
     */
    public List<Animal> readAllAnimals() {
        return getAnimalList();
    }



    /**
     * @param zooUUID die UUID des Zoos
     * @return den Zoo (null=not found)
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
     * Fügt einen neuen Zoo hinzu
     * @param zoo der neue Zoo
     */
    public void insertZoo(Zoo zoo) {
        getZooList().add(zoo);
        writeZooJSON();
    }

    /**
     * Die Liste der Zoos updaten
     */
    public void upadteZoo() {
        writeZooJSON();
    }

    /**
     * @param zooUUID  die UUID des Zoos, welcher gelöscht werden soll
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
     * @return Eine Liste aller Zoos
     */

    private List<Zoo> getZooList() {
        return zooList;
    }

    /***
     * @param zooList die Liste der Zoos, welche gesetzt werden soll
     */

    private void setZooList(List<Zoo> zooList) {
        this.zooList = zooList;
    }

    /**
     * Liest die Zoos aus einer JSON Datei aus
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
     * Schreibt die Zoos in eine JSON Datei
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
     * @param gehegeUUID die UUID des Geheges, welches gelsen werden soll
     * @return das Gehege (null=not found)
     */
    public Gehege readGehegeByUUID(String gehegeUUID) {
        Gehege gehege = null;
        for (Gehege entry : getGehegeList()) {
            if (entry.getGehegeUUID().equals(gehegeUUID)) {
                gehege = entry;
            }
        }
        return gehege;
    }

    /**
     * @param gehege fügt ein neues Gehege in die Liste der Gehege ein
     */
    public void insertGehege(Gehege gehege) {
        getGehegeList().add(gehege);
        writeGehegeJSON();
    }

    /**
     * Updated die Liste der Gehege
     */
    public void updateGehege() {
        writeGehegeJSON();
    }

    /**
     * @param gehegeUUID die UUID des Geheges, welches gelöscht werden soll
     * @return  success=true/false
     */
    public boolean deleteGehege(String gehegeUUID) {
        Gehege gehege = readGehegeByUUID(gehegeUUID);
        if (gehege != null) {
            getGehegeList().remove(gehege);
            writeGehegeJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Liest alle Gehege aus einer JSON Datei
     */
    private void readGehegeJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("gehegeJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Gehege[] gehegeArray = objectMapper.readValue(jsonData, Gehege[].class);
            for (Gehege gehege : gehegeArray) {
                getGehegeList().add(gehege);
                System.out.println(gehege);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Schreibt die Liste aller Gehege in eine JSON Datei
     */
    private void writeGehegeJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String bookPath = Config.getProperty("gehegeJSON");
        try {
            fileOutputStream = new FileOutputStream(bookPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getGehegeList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @return eine Liste aller Gehege
     */

    private List<Gehege> getGehegeList() {
        return gehegeList;
    }

    /**
     * @param gehegeList setzt die Liste aller Gehege
     */

    private void setGehegeList(List<Gehege> gehegeList) {
        this.gehegeList = gehegeList;
    }


    /**
     * @param animalUUID die UUID des Tiers, welches gelsen werden soll
     * @return das Tier (null=not found)
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
     * Updated die Liste der Tiere
     */
    public void updateAnimal() {
        writeAnimalJSON();
    }

    /**
     * @param animalUUID die UUID des Tiers, welches gelöscht werden soll
     * @return  success=true/false
     */
    public boolean deleteAnimal(String animalUUID) {
        Animal animal = readAnimalByUUID(animalUUID);
        if (animal != null) {
            getGehegeList().remove(animal);
            writeAnimalJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Liest eine Liste aller Tiere aus einer JSON Datei
     */
    private void readAnimalJSON() {
        try {
            String path = Config.getProperty("tierJSON");
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
     * Schreibt eine Liste aller Tiere in eine JSON Datei
     */
    private void writeAnimalJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String bookPath = Config.getProperty("tierJSON");
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
     * @return eine Liste aller Tiere
     */

    private List<Animal> getAnimalList() {
        return animalList;
    }

    /**
     * @param animalList setzt eine Liste aller Tiere
     */

    private void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }

}