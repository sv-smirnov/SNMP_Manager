package ru.rtrn.entity;

import org.snmp4j.smi.Variable;

import java.util.Map;

public class Param {
    private String name;
    private String oid;
    private String decription;
    private Map<String, String> values;

    public Param(String name, String oid, String decription) {
        this.name = name;
        this.oid = oid;
        this.decription = decription;
    }
    public Param(String name, String oid, String decription, Map<String, String> values) {
        this.name = name;
        this.oid = oid;
        this.decription = decription;
        this.values = values;
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

    public Map<String, String> getValues() {
        return values;
    }
}
