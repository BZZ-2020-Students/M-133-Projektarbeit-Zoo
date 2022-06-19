package dev.muetzilla.m133projektarbeitzoo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.muetzilla.m133projektarbeitzoo.data.DataHandler;

/**
 * @Author: Moritz
 * @Date: 2022-05-16
 * @Since 1.0.0-SNAPSHOT
 *
 * @Description: An enclosure in a zoo in which animals live
 *
 */
public class Enclosure {
    private String enclosureUUID;
    private Integer length;
    private Integer width;
    private String kindOfEnclosure;

    @JsonIgnore
    private Zoo zoo;


    /**
     * Default Constructor des Geheges
     */
    public Enclosure(){

    }

    /**
     *
     * @param enclosureUUID the uuid of the enclosure
     * @param length the length the enclosure has
     * @param width the width the enclosure has
     * @param kindOfEnclosure the enclosure is
     * @param zoo the zoo in which the enclosure is
     */
    public Enclosure(String enclosureUUID, Integer length, Integer width, String kindOfEnclosure, Zoo zoo) {
        this.enclosureUUID = enclosureUUID;
        this.length = length;
        this.width = width;
        this.kindOfEnclosure = kindOfEnclosure;
        this.zoo = zoo;
    }


    /**
     *
     * @return the zoo in which the enclosure is located
     */
    public Zoo getZoo() {
        return zoo;
    }

    /**
     *
     * @param zoo sets the zoo in which the enclosure is
     */
    public void setZoo(Zoo zoo) {
        this.zoo = zoo;
    }

    /**
     *
     * @return the UUID of the enclosure
     */
    public String getEnclosureUUID() {
        return enclosureUUID;
    }

    /**
     *
     * @param enclosureUUID sets the UUID of the enclosure
     */
    public void setEnclosureUUID(String enclosureUUID) {
        this.enclosureUUID = enclosureUUID;
    }

    /**
     *
     * @return the length the enclosure has
     */
    public Integer getLength() {
        return length;
    }

    /**
     *
     * @param length sets the length the enclosure has
     */
    public void setLength(Integer length) {
        this.length = length;
    }

    /**
     *
     * @return the width the enclosure has
     */
    public Integer getWidth() {
        return width;
    }

    /**
     *
     * @param width sets the width the enclosure has
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     *
     * @return the kind of which the enclosure is
     */
    public String getKindOfEnclosure() {
        return kindOfEnclosure;
    }

    /**
     *
     * @param kindOfEnclosure sets the type of the enclosure
     */
    public void setKindOfEnclosure(String kindOfEnclosure) {
        this.kindOfEnclosure = kindOfEnclosure;
    }


    /**
     *
     * @return the UUID in which the enclosure is
     */
    public String getZooUUID() {
        return getZoo().getZooUUID();
    }


    /**
     * Sets the values of the zoo
     * @param zooUUID sets the UUID the enclosure of the zoo is in
     */
    public void setZooUUID(String zooUUID) {
        setZoo(new Zoo());
        Zoo zoo = DataHandler.getInstance().readZooByUUID(zooUUID);
        getZoo().setZooUUID(zooUUID);
        getZoo().setZooName(zoo.getZooName());

    }
}
