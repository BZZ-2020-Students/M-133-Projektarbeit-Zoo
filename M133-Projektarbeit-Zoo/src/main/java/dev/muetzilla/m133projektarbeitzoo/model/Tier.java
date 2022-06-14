package dev.muetzilla.m133projektarbeitzoo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.muetzilla.m133projektarbeitzoo.data.DataHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

/**
 * @Author: Moritz
 * @Date: 2022-05-16
 * @Since 1.0.0-SNAPSHOT
 *
 * @Description: Ein Tier, welches in einem Zoo lebt
 *
 */
public class Tier {
    private String tierUUID;
    private String name;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date geburtsdatum;
    private Integer anzahlBeine;
    private String geschlecht;
    private ArrayList<String> futter;
    private String gehegeArt;
    private String lebensraum;
    private String gattung;

    @JsonIgnore
    private Gehege gehege;

    /**
     * Default Constructor des Geheges
     */
    public Tier(){

    }

    /**
     * Constructor für das Tier
     * @param tierUUID UUID des Tiers
     * @param name Name des Tiers
     * @param geburtsdatum Geburtsdatum des Tiers
     * @param anzahlBeine Anzahl der Beine des Tiers
     * @param geschlecht Geschlecht des Tiers
     * @param futter Futter, welches das Tier bekommt
     * @param gehegeArt Art des Geheges, in dem das Tier lebt
     * @param lebensraum Lebensraum, in welchem das Tier natürlich lebt
     * @param gehege Gehege, in dem das Tier lebt
     * @param gattung Gattung des Tieres
     */
    public Tier(String tierUUID, String name, Date geburtsdatum, Integer anzahlBeine, String geschlecht, ArrayList<String> futter, String gehegeArt, String lebensraum, Gehege gehege, String gattung) {
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

    /**
     * @return die UUID des Tieres
     */
    public String getTierUUID() {
        return tierUUID;
    }

    /**
     * @param tierUUID UUID des Tieres
     */
    public void setTierUUID(String tierUUID) {
        this.tierUUID = tierUUID;
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
    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    /**
     * @param geburtsdatum Datum, an welchem das Tier geboren wurde
     */
    public void setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    /**
     * @return Anzahl der Beine, welche das Tier hat
     */
    public Integer getAnzahlBeine() {
        return anzahlBeine;
    }

    /**
     * @param anzahlBeine Anzahl der Beine, welche das Tier hat
     */

    public void setAnzahlBeine(Integer anzahlBeine) {
        this.anzahlBeine = anzahlBeine;
    }

    /**
     * @return Geschlecht des Tieres
     */
    public String getGeschlecht() {
        return geschlecht;
    }

    /**
     * @param geschlecht Geschlecht des Tieres
     */
    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }

    /**
     * @return Futter, welches das Tier bekommt
     */
    public ArrayList<String> getFutter() {
        return futter;
    }

    /**
     * @param futter Futter, welches das Tier bekommt
     */
    public void setFutter(ArrayList<String> futter) {
        this.futter = futter;
    }

    /**
     * @return Art des Geheges, in dem das Tier lebt
     */
    public String getGehegeArt() {
        return gehegeArt;
    }

    /**
     * @param gehegeArt Art des Geheges, in dem das Tier lebt
     */
    public void setGehegeArt(String gehegeArt) {
        this.gehegeArt = gehegeArt;
    }

    /**
     * @return Lebensraum, in dem das Tier natürlich lebt
     */
    public String getLebensraum() {
        return lebensraum;
    }

    /**
     * @param lebensraum Lebensraum, in welchem das Tier natürlich lebt
     */
    public void setLebensraum(String lebensraum) {
        this.lebensraum = lebensraum;
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
    public String getGattung() {
        return gattung;
    }

    /**
     * @param gattung Gattung des Tieres
     */
    public void setGattung(String gattung) {
        this.gattung = gattung;
    }

    /**
     * @return UUID des Geheges, in dem das Tier lebt
     */
    public String getGehegeUUID() {
        return getGehege().getGehegeUUID();
    }

    /**
     * @param gehegeUUID UUID des Geheges, in dem das Tier lebt
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

}
