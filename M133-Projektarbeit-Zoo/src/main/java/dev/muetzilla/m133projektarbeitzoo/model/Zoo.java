package dev.muetzilla.m133projektarbeitzoo.model;

import java.util.ArrayList;

public class Zoo {
    private String zooUUID;
    private String zooName;
    private ArrayList<Gehege> gehege;

    public Zoo(){

    }
    public Zoo(String zooUUID, String zooName, ArrayList<Gehege> gehege){
        this.zooUUID = zooUUID;
        this.zooName = zooName;
        this.gehege = gehege;
    }
    public Zoo(String zooUUID, String zooName){
        this.zooUUID = zooUUID;
        this.zooName = zooName;
    }
    public String getZooUUID() {
        return zooUUID;
    }

    public void setZooUUID(String zooUUID) {
        this.zooUUID = zooUUID;
    }

    public String getZooName() {
        return zooName;
    }

    public void setZooName(String zooName) {
        this.zooName = zooName;
    }

    public ArrayList<Gehege> getGehege() {
        return gehege;
    }

    public void setGehege(ArrayList<Gehege> gehege) {
        this.gehege = gehege;
    }

}
