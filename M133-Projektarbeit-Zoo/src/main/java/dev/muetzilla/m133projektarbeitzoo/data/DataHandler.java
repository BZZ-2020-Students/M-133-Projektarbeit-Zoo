package dev.muetzilla.m133projektarbeitzoo.data;


import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import dev.muetzilla.m133projektarbeitzoo.model.Gehege;
import dev.muetzilla.m133projektarbeitzoo.model.Tier;
import dev.muetzilla.m133projektarbeitzoo.model.Zoo;
import dev.muetzilla.m133projektarbeitzoo.service.Config;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and writes the data in the JSON-files
 */
public class DataHandler {
    private static DataHandler instance = null;
    private List<Zoo> zooList;
    private List<Gehege> gehegeList;
    private List<Tier> tierList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setZooList(new ArrayList<>());
        readZooJSON();
        setGehegeList(new ArrayList<>());
        readGehegeJSON();
        setTierList(new ArrayList<>());
        readTierJSON();
    }

    /**
     * gets the only instance of this class
     * @return
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }


    /**
     * reads all books
     * @return list of books
     */
    public List<Zoo> readAllZoos() {
        return getZooList();
    }

    /**
     * reads a zoo by its uuid
     * @param zooUUID
     * @return the Zoo (null=not found)
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
     * reads a gehege by its uuid
     * @param gehegeUUID
     * @return the Gehege (null=not found)
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
     * inserts a new book into the bookList
     *
     * @param zoo the book to be saved
     */
    public void insertZoo(Zoo zoo) {
        getZooList().add(zoo);
        writeZooJSON();
    }

    /**
     * updates the bookList
     */
    public void upadteZoo() {
        writeZooJSON();
    }

    /**
     * deletes a book identified by the bookUUID
     * @param zooUUID  the key
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
     * reads all publishers
     * @return list of books
     */
    public List<Gehege> readAllGehege() {
        return gehegeList;
    }

    /**
     * reads a publisher by its uuid
     * @param tierUUID
     * @return the Publisher (null=not found)
     */
    public Tier readTierByUUID(String tierUUID) {
        Tier tier = null;
        for (Tier entry : getTierList()) {
            if (entry.getTierUUID().equals(tierUUID)) {
                tier = entry;
            }
        }
        return tier;
    }

    /**
     * inserts a new publisher into the bookList
     *
     * @param gehege the publisher to be saved
     */
    public void insertGehege(Gehege gehege) {
        getGehegeList().add(gehege);
        writeGehegeJSON();
    }

    /**
     * updates the publisherList
     */
    public void updateGehege() {
        writeGehegeJSON();
    }

    /**
     * updates the publisherList
     */
    public void updateZoo() {
        writeZooJSON();
    }

    /**
     * updates the publisherList
     */
    public void updateTier() {
        writeTierJSON();
    }

    /**
     * deletes a publisher identified by the publisherUUID
     * @param gehegeUUID  the key
     * @return  success=true/false
     */
    public boolean deleteGehege(String gehegeUUID) {
        Gehege publisher = readGehegeByUUID(gehegeUUID);
        if (publisher != null) {
            getGehegeList().remove(publisher);
            writeGehegeJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * reads the books from the JSON-file
     */
    private void readTierJSON() {
        try {
            String path = Config.getProperty("tierJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Tier[] tiere = objectMapper.readValue(jsonData, Tier[].class);
            for (Tier tier : tiere) {
                getTierList().add(tier);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * writes the bookList to the JSON-file
     */
    private void writeTierJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String bookPath = Config.getProperty("tierJSON");
        try {
            fileOutputStream = new FileOutputStream(bookPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getTierList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * reads the books from the JSON-file
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
     * writes the bookList to the JSON-file
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
     * reads the publishers from the JSON-file
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
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * writes the publisherList to the JSON-file
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
     * gets zooList
     *
     * @return value of zooList
     */

    private List<Zoo> getZooList() {
        return zooList;
    }

    /**
     * sets zooList
     *
     * @param zooList the value to set
     */

    private void setZooList(List<Zoo> zooList) {
        this.zooList = zooList;
    }

    /**
     * gets gehegeList
     *
     * @return value of gehegeList
     */

    private List<Gehege> getGehegeList() {
        return gehegeList;
    }

    /**
     * sets gehegeList
     *
     * @param gehegeList the value to set
     */

    private void setGehegeList(List<Gehege> gehegeList) {
        this.gehegeList = gehegeList;
    }

    /**
     * gets tierlist
     *
     * @return value of tierList
     */

    private List<Tier> getTierList() {
        return tierList;
    }

    /**
     * sets tierlist
     *
     * @param tierList the value to set
     */

    private void setTierList(List<Tier> tierList) {
        this.tierList = tierList;
    }


}