package dev.muetzilla.m133projektarbeitzoo.model;

import java.util.ArrayList;

/**
 * @Author: Moritz
 * @Date: 2022-05-16
 * @Since 1.0.0-SNAPSHOT
 *
 * @Description: Ein Zoo, welcher verschiedene Gehege hat, in welchen wiederrum verschiedene Tiere leben
 *
 */
public class Zoo {
    private String zooUUID;
    private String zooName;
    /**
     * Default Constructor des Zoos
     */
    public Zoo(){

    }

    /**
     * Constructor des Geheges
     *
     * @param zooUUID UUID des Zoos
     * @param zooName Name des Zoos
     */
    public Zoo(String zooUUID, String zooName){
        this.zooUUID = zooUUID;
        this.zooName = zooName;
    }


    /**
     * @return UUID des Zoos
     */
    public String getZooUUID() {
        return zooUUID;
    }

    /**
     * @param zooUUID UUID des Zoos
     */
    public void setZooUUID(String zooUUID) {
        this.zooUUID = zooUUID;
    }

    /**
     * @return Name des Zoos
     */
    public String getZooName() {
        return zooName;
    }

    /**
     * @param zooName Name des Zoos
     */
    public void setZooName(String zooName) {
        this.zooName = zooName;
    }

}
