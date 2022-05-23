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
    public List<Tier> readAllTiere() {
        return getTierList();
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
     * @param tierUUID die UUID des Tiers, welches gelsen werden soll
     * @return das Tier (null=not found)
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
     * Updated die Liste der Tiere
     */
    public void updateTier() {
        writeTierJSON();
    }

    /**
     * @param tierUUID die UUID des Tiers, welches gelöscht werden soll
     * @return  success=true/false
     */
    public boolean deleteTier(String tierUUID) {
        Tier tier = readTierByUUID(tierUUID);
        if (tier != null) {
            getGehegeList().remove(tier);
            writeTierJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Liest eine Liste aller Tiere aus einer JSON Datei
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
     * Schreibt eine Liste aller Tiere in eine JSON Datei
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
     * @return eine Liste aller Tiere
     */

    private List<Tier> getTierList() {
        return tierList;
    }

    /**
     * @param tierList setzt eine Liste aller Tiere
     */

    private void setTierList(List<Tier> tierList) {
        this.tierList = tierList;
    }

}