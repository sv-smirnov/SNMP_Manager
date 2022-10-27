package ru.rtrn.entity;

import java.util.ArrayList;
import java.util.Arrays;

public class Station {
    private String name;
    private String ip;
    private ArrayList<String> devices;
    public Station() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public ArrayList<String> getDevices() {
        return devices;
    }

    public void setDevices(ArrayList<String> devices) {
        this.devices = devices;
    }
    public void setDevicesByString(String devicesByString) {
        this.devices = new ArrayList<String>(Arrays.asList(devicesByString.trim().split("\\s*,\\s*")));
    }
}
