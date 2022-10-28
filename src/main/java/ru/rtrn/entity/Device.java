package ru.rtrn.entity;

import java.util.ArrayList;

public abstract class Device {
    private ArrayList<Param> params;

    public abstract String getCommunity();

    public abstract String getPort();

    public abstract void initParams();

    public abstract ArrayList<Param> getParams();

    public Param getParamByName(String name){
        Param param = params.get(0);
        for (Param p:params
        ) {if (p.getName().equals(name))
            param = p;
        }
        return param;
    }
}
