package ru.rtrn.entity;

import java.util.ArrayList;

public abstract class Device {
    public Device() {
        initParams();
    }

    private ArrayList<Param> params;

    public abstract String getCommunity();

    public abstract String getPort();

    public abstract void initParams();

    public abstract ArrayList<Param> getParams();

    public abstract Param getParamByName(String name);
}
