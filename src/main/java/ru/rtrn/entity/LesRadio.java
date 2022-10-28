package ru.rtrn.entity;

import java.util.ArrayList;


public class LesRadio extends Device {
    private static final String type = "SW-212HDAE";
    private final String community = "private";
    //    private static final File file = new File("src/main/resources/mibs/1.csv");
    private ArrayList<Param> params;
    private String port = "8043";

    public LesRadio() {
        initParams();
    }

    public LesRadio(String port) {
        initParams();
        this.port = port;
    }

    @Override
    public String getCommunity() {
        return community;
    }

    @Override
    public String getPort() {
        return port;
    }

    public void initParams() {
        params = new ArrayList<>();
        params.add(new Param("Output", ".1.3.6.1.4.1.52491.18.1.0", "Select input. A=0/B=1"));
        params.add(new Param("Automatic", ".1.3.6.1.4.1.52491.18.2.0", "Enable/disable automatic switching. Disable = 0, enable = 1"));
    }

    @Override
    public ArrayList<Param> getParams() {
        return params;
    }
    public Param getParamByName(String name){
        Param param = params.get(0);
        for (Param p:params
        ) {if (p.getName().equals(name))
            param = p;
        }
        return param;
    }

}
