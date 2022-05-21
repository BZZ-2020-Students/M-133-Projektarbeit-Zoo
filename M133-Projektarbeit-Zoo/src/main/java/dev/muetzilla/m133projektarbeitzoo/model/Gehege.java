package dev.muetzilla.m133projektarbeitzoo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.muetzilla.m133projektarbeitzoo.data.DataHandler;

import java.util.ArrayList;

public class Gehege {
    private String gehegeUUID;
    private Integer laenge;
    private Integer breite;
    private String gehegeArt;

    private ArrayList<Tier> tiere;

    @JsonIgnore
    private Zoo zoo;



    public Gehege(){

    }

    public Gehege(String gehegeUUID, Integer laenge, Integer breite, String gehegeArt, ArrayList<Tier> tiere,  Zoo zoo) {
        this.gehegeUUID = gehegeUUID;
        this.laenge = laenge;
        this.breite = breite;
        this.gehegeArt = gehegeArt;
        this.tiere = tiere;
        this.zoo = zoo;
    }


    public Zoo getZoo() {
        return zoo;
    }

    public void setZoo(Zoo zoo) {
        this.zoo = zoo;
    }

    public String getGehegeUUID() {
        return gehegeUUID;
    }

    public void setGehegeUUID(String gehegeUUID) {
        this.gehegeUUID = gehegeUUID;
    }

    public Integer getLaenge() {
        return laenge;
    }

    public void setLaenge(Integer laenge) {
        this.laenge = laenge;
    }

    public Integer getBreite() {
        return breite;
    }

    public void setBreite(Integer breite) {
        this.breite = breite;
    }

    public ArrayList<Tier> getTiere() {
        return tiere;
    }

    public void setTiere(ArrayList<Tier> tiere) {
        this.tiere = tiere;
    }

    public String getGehegeArt() {
        return gehegeArt;
    }

    public void setGehegeArt(String gehegeArt) {
        this.gehegeArt = gehegeArt;
    }


    /**
     * gets the publisherUUID from the Publisher-object
     * @return
     */
    public String getZooUUID() {
        return getZoo().getZooUUID();
    }

    /**
     * creates a Publisher-object without the booklist
     * @param zooUUID
     */
    public void setZooUUID(String zooUUID) {
        setZoo(new Zoo());
        Zoo zoo = DataHandler.getInstance().readZooByUUID(zooUUID);
        getZoo().setZooUUID(zooUUID);
        getZoo().setZooName(zoo.getZooName());

    }
}
