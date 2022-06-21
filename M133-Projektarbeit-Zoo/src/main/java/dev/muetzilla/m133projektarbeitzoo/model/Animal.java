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
 * @Description: An animal which lives in an enclosure of a zoo
 *
 */
public class Animal {
    private String animalUUID;
    private String name;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthday;
    private Integer amountOfLegs;
    private String gender;
    private String feed;
    private String kindOfEnclosure;
    private String biotop;
    private String kindOfAnimal;

    @JsonIgnore
    private Enclosure enclosure;

    /**
     * Default Constructor des Geheges
     */
    public Animal(){

    }

    /**
     *
     * @param animalUUID the UUID of the animal
     * @param name the name of the animal
     * @param birthday the birthday of the animal
     * @param amountOfLegs the amount of legs the animal has (must be an even number since there are no animal with an odd number of legs)
     * @param gender the gender of the animal
     * @param kindOfEnclosure the kind of enclosure the animal lives in
     * @param biotop the biotop the animal would live in the wild
     * @param feed the feed the animal gets
     * @param enclosure the enclosure in which the animal lives
     * @param kindOfAnimal the race of the animal
     */
    public Animal(String animalUUID, String name, Date birthday, Integer amountOfLegs, String gender, String kindOfEnclosure, String biotop, String feed, Enclosure enclosure, String kindOfAnimal) {
        this.animalUUID = animalUUID;
        this.name = name;
        this.birthday = birthday;
        this.amountOfLegs = amountOfLegs;
        this.gender = gender;
        this.feed = feed;
        this.kindOfEnclosure = kindOfEnclosure;
        this.biotop = biotop;
        this.enclosure = enclosure;
        this.kindOfAnimal = kindOfAnimal;
    }

    /**
     * @return the UUID of the animal
     */
    public String getAnimalUUID() {
        return animalUUID;
    }

    /**
     * @param animalUUID the UUID of the animal
     */
    public void setAnimalUUID(String animalUUID) {
        this.animalUUID = animalUUID;
    }

    /**
     * @return the name of the animal
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name of the animal
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the date on which the animal was born
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday  the date on which the animal was born
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @return amount of legs the animal has
     */
    public Integer getAmountOfLegs() {
        return amountOfLegs;
    }

    /**
     * @param amountOfLegs amount of legs the animal has
     */

    public void setAmountOfLegs(Integer amountOfLegs) {
        this.amountOfLegs = amountOfLegs;
    }

    /**
     * @return the gender of the animal
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender of the animal
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the feed  the animal gets
     */
    public String getFeed() {
        return feed;
    }

    /**
     * @param feed the feed the animal gets
     */
    public void setFeed(String feed) {
        this.feed = feed;
    }

    /**
     * @return the kind of enclosure the animal lives in
     */
    public String getKindOfEnclosure() {
        return kindOfEnclosure;
    }

    /**
     * @param kindOfEnclosure  the kind of enclosure the animal lives in
     */
    public void setKindOfEnclosure(String kindOfEnclosure) {
        this.kindOfEnclosure = kindOfEnclosure;
    }

    /**
     * @return the biotop the animal would live in the wild
     */
    public String getBiotop() {
        return biotop;
    }

    /**
     * @param biotop the biotop the animal would live in the wild
     */
    public void setBiotop(String biotop) {
        this.biotop = biotop;
    }

    /**
     * @return enclosure in which the animal lives
     */
    public Enclosure getEnclosure() {
        return enclosure;
    }


    /**
     *
     * @param enclosure the enclosure in which the animal lives
     */
    public void setEnclosure(Enclosure enclosure) {
        this.enclosure = enclosure;
    }

    /**
     * @return the race of the animal
     */
    public String getKindOfAnimal() {
        return kindOfAnimal;
    }

    /**
     * @param kindOfAnimal the race of the animal
     */
    public void setKindOfAnimal(String kindOfAnimal) {
        this.kindOfAnimal = kindOfAnimal;
    }

    /**
     * @return the UUID of the enclosure in which the animal lives
     */
    public String getEnclosureUUID() {
        return getEnclosure().getEnclosureUUID();
    }

    /**
     * @param enclosureUUID the UUID in which the animal lives
     */
    public void setEnclosureUUID(String enclosureUUID) {
        setEnclosure(new Enclosure());
        Enclosure enclosure = DataHandler.getInstance().readEnclosureByUUID(enclosureUUID);
        getEnclosure().setEnclosureUUID(enclosureUUID);
        getEnclosure().setKindOfEnclosure(enclosure.getKindOfEnclosure());
        getEnclosure().setWidth(enclosure.getWidth());
        getEnclosure().setLength(enclosure.getLength());
        getEnclosure().setZooUUID(enclosure.getZooUUID());
    }

}
