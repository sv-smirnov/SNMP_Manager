package ru.rtrn.entity;

public class Param {
    private String name;
    private String oid;
    private String decription;

    public Param(String name, String oid, String decription) {
        this.name = name;
        this.oid = oid;
        this.decription = decription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }
}
