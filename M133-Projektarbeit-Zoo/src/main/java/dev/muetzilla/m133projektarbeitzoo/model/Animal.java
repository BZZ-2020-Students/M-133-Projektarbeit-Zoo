package dev.muetzilla.m133projektarbeitzoo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.muetzilla.m133projektarbeitzoo.data.DataHandler;

import java.util.ArrayList;
import java.util.Date;

/**
 * @Author: Moritz
 * @Date: 2022-05-16
 * @Since 1.0.0-SNAPSHOT
 *
 * @Description: Ein Tier, welches in einem Zoo lebt
 *
 */
public class Animal {
    private String animalUUID;
    private String name;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthday;
    private Integer amountOfLegs;
    private String gender;
    private ArrayList<String> feed;
    private String kindOfEnclosure;
    private String biotop;
    private String kindOfAnimal;

    @JsonIgnore
    private Gehege gehege;

    /**
     * Default Constructor des Geheges
     */
    public Animal(){

    }

    /**
     * Constructor für das Tier
     * @param tierUUID UUID des Tiers
     * @param name Name des Tiers
     * @param geburtsdatum Geburtsdatum des Tiers
     * @param amountOfLegs Anzahl der Beine des Tiers
     * @param geschlecht Geschlecht des Tiers
     * @param futter Futter, welches das Tier bekommt
     * @param gehegeArt Art des Geheges, in dem das Tier lebt
     * @param lebensraum Lebensraum, in welchem das Tier natürlich lebt
     * @param gehege Gehege, in dem das Tier lebt
     * @param gattung Gattung des Tieres
     */
    public Animal(String tierUUID, String name, Date geburtsdatum, Integer amountOfLegs, String geschlecht, ArrayList<String> futter, String gehegeArt, String lebensraum, Gehege gehege, String gattung) {
        this.animalUUID = tierUUID;
        this.name = name;
        this.birthday = geburtsdatum;
        this.amountOfLegs = amountOfLegs;
        this.gender = geschlecht;
        this.feed = futter;
        this.kindOfEnclosure = gehegeArt;
        this.biotop = lebensraum;
        this.gehege = gehege;
        this.kindOfAnimal = gattung;
    }

    /**
     * @return die UUID des Tieres
     */
    public String getAnimalUUID() {
        return animalUUID;
    }

    /**
     * @param animalUUID UUID des Tieres
     */
    public void setAnimalUUID(String animalUUID) {
        this.animalUUID = animalUUID;
    }

    /**
     * @return Name des Tieres
     */
    public String getName() {
        return name;
    }

    /**
     * @param name Name des Tieres
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Datum, an welchem das Tier geboren wurde
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday Datum, an welchem das Tier geboren wurde
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @return Anzahl der Beine, welche das Tier hat
     */
    public Integer getAmountOfLegs() {
        return amountOfLegs;
    }

    /**
     * @param amountOfLegs Anzahl der Beine, welche das Tier hat
     */

    public void setAmountOfLegs(Integer amountOfLegs) {
        this.amountOfLegs = amountOfLegs;
    }

    /**
     * @return Geschlecht des Tieres
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender Geschlecht des Tieres
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return Futter, welches das Tier bekommt
     */
    public ArrayList<String> getFeed() {
        return feed;
    }

    /**
     * @param feed Futter, welches das Tier bekommt
     */
    public void setFeed(ArrayList<String> feed) {
        this.feed = feed;
    }

    /**
     * @return Art des Geheges, in dem das Tier lebt
     */
    public String getKindOfEnclosure() {
        return kindOfEnclosure;
    }

    /**
     * @param kindOfEnclosure Art des Geheges, in dem das Tier lebt
     */
    public void setKindOfEnclosure(String kindOfEnclosure) {
        this.kindOfEnclosure = kindOfEnclosure;
    }

    /**
     * @return Lebensraum, in dem das Tier natürlich lebt
     */
    public String getBiotop() {
        return biotop;
    }

    /**
     * @param biotop Lebensraum, in welchem das Tier natürlich lebt
     */
    public void setBiotop(String biotop) {
        this.biotop = biotop;
    }

    /**
     * @return Gehege, in dem das Tier lebt
     */
    public Gehege getGehege() {
        return gehege;
    }

    /**
     * @param gehege Gehege, in dem das Tier lebt
     */
    public void setGehege(Gehege gehege) {
        this.gehege = gehege;
    }

    /**
     * @return Gattung des Tieres
     */
    public String getKindOfAnimal() {
        return kindOfAnimal;
    }

    /**
     * @param kindOfAnimal Gattung des Tieres
     */
    public void setKindOfAnimal(String kindOfAnimal) {
        this.kindOfAnimal = kindOfAnimal;
    }

    /**
     * @return UUID des Geheges, in dem das Tier lebt
     */
    public String getEnclosureUUID() {
        return getGehege().getGehegeUUID();
    }

    /**
     * @param gehegeUUID UUID des Geheges, in dem das Tier lebt
     */
    public void setEnclosureUUID(String gehegeUUID) {
        setGehege(new Gehege());
        Gehege gehege = DataHandler.getInstance().readGehegeByUUID(gehegeUUID);
        getGehege().setGehegeUUID(gehegeUUID);
        getGehege().setGehegeArt(gehege.getGehegeArt());
        getGehege().setBreite(gehege.getBreite());
        getGehege().setLaenge(gehege.getLaenge());
        getGehege().setZooUUID(gehege.getZooUUID());

    }

}
