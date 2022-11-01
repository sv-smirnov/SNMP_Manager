package ru.rtrn.entity;

import java.util.ArrayList;

public class Uax extends Device{
    private static final String type = "Uax";
    private final String community = "private";
    private ArrayList<Param> params;
    private String port = "8048";

    public Uax() {
        initParams();
    }

    public Uax(String port) {
        this.port = port;
        initParams();
    }

    @Override
    public String getCommunity() {
        return community;
    }

    @Override
    public String getPort() {
        return port;
    }

    @Override
    public void initParams() {
        params = new ArrayList<>();

    }

    @Override
    public ArrayList<Param> getParams() {
        return params;
    }

    @Override
    public Param getParamByName(String name) {
        Param param = params.get(0);
        for (Param p : params
        ) {
            if (p.getName().equals(name))
                param = p;
        }
        return param;
    }
}
