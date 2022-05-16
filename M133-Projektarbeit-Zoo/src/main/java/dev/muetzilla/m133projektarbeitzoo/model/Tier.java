package dev.muetzilla.m133projektarbeitzoo.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Tier {
    private String tierUUID;
    private String name;
    private LocalDate geburtsdatum;
    private Integer anzahlBeine;
    private Character Geschelcht;
    private ArrayList<String> futter;
    private GehegeArt gehegeArt;
    private Lebensraum lebensraum;


    public Tier(){

    }

    public Tier(String tierUUID, String name, LocalDate geburtsdatum, Integer anzahlBeine, Character geschelcht, ArrayList<String> futter, GehegeArt gehegeArt, Lebensraum lebensraum) {
        this.tierUUID = tierUUID;
        this.name = name;
        this.geburtsdatum = geburtsdatum;
        this.anzahlBeine = anzahlBeine;
        Geschelcht = geschelcht;
        this.futter = futter;
        this.gehegeArt = gehegeArt;
        this.lebensraum = lebensraum;
    }

    public String getTierUUID() {
        return tierUUID;
    }

    public void setTierUUID(String tierUUID) {
        this.tierUUID = tierUUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public Integer getAnzahlBeine() {
        return anzahlBeine;
    }

    public void setAnzahlBeine(Integer anzahlBeine) {
        this.anzahlBeine = anzahlBeine;
    }

    public Character getGeschelcht() {
        return Geschelcht;
    }

    public void setGeschelcht(Character geschelcht) {
        Geschelcht = geschelcht;
    }

    public ArrayList<String> getFutter() {
        return futter;
    }

    public void setFutter(ArrayList<String> futter) {
        this.futter = futter;
    }

    public GehegeArt getGehegeArt() {
        return gehegeArt;
    }

    public void setGehegeArt(GehegeArt gehegeArt) {
        this.gehegeArt = gehegeArt;
    }

    public Lebensraum getLebensraum() {
        return lebensraum;
    }

    public void setLebensraum(Lebensraum lebensraum) {
        this.lebensraum = lebensraum;
    }
}
