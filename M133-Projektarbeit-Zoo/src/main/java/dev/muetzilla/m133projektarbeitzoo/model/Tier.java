package dev.muetzilla.m133projektarbeitzoo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.muetzilla.m133projektarbeitzoo.data.DataHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class Tier {
    private String tierUUID;
    private String name;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date geburtsdatum;
    private Integer anzahlBeine;
    private Character geschlecht;
    private ArrayList<String> futter;
    private String gehegeArt;
    private String lebensraum;
    private String gattung;

    @JsonIgnore
    private Gehege gehege;


    public Tier(){

    }

    public Tier(String tierUUID, String name, Date geburtsdatum, Integer anzahlBeine, Character geschlecht, ArrayList<String> futter, String gehegeArt, String lebensraum, Gehege gehege, String gattung) {
        this.tierUUID = tierUUID;
        this.name = name;
        this.geburtsdatum = geburtsdatum;
        this.anzahlBeine = anzahlBeine;
        this.geschlecht = geschlecht;
        this.futter = futter;
        this.gehegeArt = gehegeArt;
        this.lebensraum = lebensraum;
        this.gehege = gehege;
        this.gattung = gattung;
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

    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public Integer getAnzahlBeine() {
        return anzahlBeine;
    }

    public void setAnzahlBeine(Integer anzahlBeine) {
        this.anzahlBeine = anzahlBeine;
    }

    public Character getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(Character geschlecht) {
        this.geschlecht = geschlecht;
    }

    public ArrayList<String> getFutter() {
        return futter;
    }

    public void setFutter(ArrayList<String> futter) {
        this.futter = futter;
    }

    public String getGehegeArt() {
        return gehegeArt;
    }

    public void setGehegeArt(String gehegeArt) {
        this.gehegeArt = gehegeArt;
    }

    public String getLebensraum() {
        return lebensraum;
    }

    public void setLebensraum(String lebensraum) {
        this.lebensraum = lebensraum;
    }

    public Gehege getGehege() {
        return gehege;
    }

    public void setGehege(Gehege gehege) {
        this.gehege = gehege;
    }

    public String getGattung() {
        return gattung;
    }

    public void setGattung(String gattung) {
        this.gattung = gattung;
    }

    /**
     * gets the publisherUUID from the Publisher-object
     * @return
     */
    public String getGehegeUUID() {
        return getGehege().getGehegeUUID();
    }

    /**
     * creates a Publisher-object without the booklist
     * @param gehegeUUID
     */
    public void setGehegeUUID(String gehegeUUID) {
        setGehege(new Gehege());
        Gehege gehege = DataHandler.getInstance().readGehegeByUUID(gehegeUUID);
        getGehege().setGehegeUUID(gehegeUUID);
        getGehege().setGehegeArt(gehege.getGehegeArt());
        getGehege().setBreite(gehege.getBreite());
        getGehege().setLaenge(gehege.getLaenge());
        getGehege().setZooUUID(gehege.getZooUUID());

    }

    /**
     * gets the publisherUUID from the Publisher-object
     * @return
     */
    public String getZooUUID() {
        return gehege.getZoo().getZooUUID();
    }

    /**
     * creates a Publisher-object without the booklist
     * @param zooUUID
     */
    public void setZooUUID(String zooUUID) {
        gehege.setZoo(new Zoo());
        Zoo zoo = DataHandler.getInstance().readZooByUUID(zooUUID);
        gehege.getZoo().setZooUUID(zooUUID);
        gehege.getZoo().setZooName(zoo.getZooName());

    }

    @Override
    public String toString() {
        return "Tier{" +
                "tierUUID='" + tierUUID + '\'' +
                ", name='" + name + '\'' +
                ", geburtsdatum=" + geburtsdatum +
                ", anzahlBeine=" + anzahlBeine +
                ", geschlecht=" + geschlecht +
                ", futter=" + futter +
                ", gehegeArt='" + gehegeArt + '\'' +
                ", lebensraum='" + lebensraum + '\'' +
                ", gattung='" + gattung + '\'' +
                ", gehege=" + gehege +
                '}';
    }
}
