package dev.muetzilla.m133projektarbeitzoo.model;

import java.util.ArrayList;

/**
 * @Author: Moritz
 * @Date: 2022-05-16
 * @Since 1.0.0-SNAPSHOT
 *
 * @Description: A zoo which has different enclosures and in these enclosures there live different animals
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
     *
     * @param zooUUID the UUID of the zoo
     * @param zooName the name of the zoo
     */
    public Zoo(String zooUUID, String zooName){
        this.zooUUID = zooUUID;
        this.zooName = zooName;
    }


    /**
     * @return the UUID of the zoo
     */
    public String getZooUUID() {
        return zooUUID;
    }

    /**
     * @param zooUUID the UUID of the zoo
     */
    public void setZooUUID(String zooUUID) {
        this.zooUUID = zooUUID;
    }

    /**
     * @return the name of the zoo
     */
    public String getZooName() {
        return zooName;
    }

    /**
     * @param zooName the name of the zoo
     */
    public void setZooName(String zooName) {
        this.zooName = zooName;
    }

}
