package dev.muetzilla.m133projektarbeitzoo.model;

import java.util.ArrayList;

public class Gehege {
    private String gehegeUUID;
    private Integer laenge;
    private Integer breite;
    private ArrayList<Tier> tiere;
    private GehegeArt gehegeArt;



    public Gehege(){

    }

    public Gehege(String gehegeUUID, Integer laenge, Integer breite, ArrayList<Tier> tiere, GehegeArt gehegeArt) {
        this.gehegeUUID = gehegeUUID;
        this.laenge = laenge;
        this.breite = breite;
        this.tiere = tiere;
        this.gehegeArt = gehegeArt;
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

    public GehegeArt getGehegeArt() {
        return gehegeArt;
    }

    public void setGehegeArt(GehegeArt gehegeArt) {
        this.gehegeArt = gehegeArt;
    }
}
