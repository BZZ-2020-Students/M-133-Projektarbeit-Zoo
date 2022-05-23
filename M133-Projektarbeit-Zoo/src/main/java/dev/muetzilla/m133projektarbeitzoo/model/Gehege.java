package dev.muetzilla.m133projektarbeitzoo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.muetzilla.m133projektarbeitzoo.data.DataHandler;

import java.util.ArrayList;

/**
 * @Author: Moritz
 * @Date: 2022-05-16
 * @Since 1.0.0-SNAPSHOT
 *
 * @Description: Ein Gehege eines Zoos, in welchem Tiere leben.
 *
 */
public class Gehege {
    private String gehegeUUID;
    private Integer laenge;
    private Integer breite;
    private String gehegeArt;

    private ArrayList<Tier> tiere;

    @JsonIgnore
    private Zoo zoo;


    /**
     * Default Constructor des Geheges
     */
    public Gehege(){

    }

    /**
     * Constructor des Geheges
     * @param gehegeUUID die UUID des Geheges
     * @param laenge die Länge des Geheges
     * @param breite die Breite des Geheges
     * @param gehegeArt die Art des Geheges
     * @param tiere die Tiere, die in diesem Gehege leben
     * @param zoo der Zoo, zu welchem das Gehege gehört
     */
    public Gehege(String gehegeUUID, Integer laenge, Integer breite, String gehegeArt, ArrayList<Tier> tiere,  Zoo zoo) {
        this.gehegeUUID = gehegeUUID;
        this.laenge = laenge;
        this.breite = breite;
        this.gehegeArt = gehegeArt;
        this.tiere = tiere;
        this.zoo = zoo;
    }


    /**
     *
     * @return den Zoo des Geheges
     */
    public Zoo getZoo() {
        return zoo;
    }

    /**
     * @param zoo der Zoo, welcher zum Gehege gehört
     */
    public void setZoo(Zoo zoo) {
        this.zoo = zoo;
    }

    /**
     * @return die UUID des Geheges
     */
    public String getGehegeUUID() {
        return gehegeUUID;
    }

    /**
     * @param gehegeUUID setzt die UUID des Geheges
     */
    public void setGehegeUUID(String gehegeUUID) {
        this.gehegeUUID = gehegeUUID;
    }

    /**
     * @return die Länge des Geheges
     */
    public Integer getLaenge() {
        return laenge;
    }

    /**
     * @param laenge wie lang ein Gehege ist
     */
    public void setLaenge(Integer laenge) {
        this.laenge = laenge;
    }

    /**
     * @return wie breit ein Gehege ist
     */
    public Integer getBreite() {
        return breite;
    }

    /**
     * @param breite setzt wie breit ein Gehege ist
     */
    public void setBreite(Integer breite) {
        this.breite = breite;
    }

    /**
     * @return Alle Tiere, die in diesem Gehege sind
     */
    public ArrayList<Tier> getTiere() {
        return tiere;
    }

    /**
     * @param tiere setzt alle Tiere, welche in einem Gehege leben
     */
    public void setTiere(ArrayList<Tier> tiere) {
        this.tiere = tiere;
    }

    /**
     * @return die Art eines Geheges (überdacht, Aquarium ...)
     */
    public String getGehegeArt() {
        return gehegeArt;
    }

    /**
     * @param gehegeArt setzt die Art eines Geheges
     */
    public void setGehegeArt(String gehegeArt) {
        this.gehegeArt = gehegeArt;
    }


    /**
     * @return die UUID des Zoos, zu welchem das Gehege gehört
     */
    public String getZooUUID() {
        return getZoo().getZooUUID();
    }

    /**
     * @param zooUUID setzt die UUID des Zoos, zu welchem das Gehege gehört
     */
    public void setZooUUID(String zooUUID) {
        setZoo(new Zoo());
        Zoo zoo = DataHandler.getInstance().readZooByUUID(zooUUID);
        getZoo().setZooUUID(zooUUID);
        getZoo().setZooName(zoo.getZooName());

    }
}
