package ru.rtrn.entity;

import java.util.ArrayList;


public class LesRadio extends Device {
    private static final String type = "SW-212HDAE";
    private final String community = "private";
    private ArrayList<Param> params;
    private String port = "8043";

    //    private static final File file = new File("src/main/resources/mibs/1.csv");

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
        params.add(new Param("Select input A/B", ".1.3.6.1.4.1.52491.18.1.0", "Select input.\n" +
                "A=0\n" +
                "B=1"));
        params.add(new Param("Automatic", ".1.3.6.1.4.1.52491.18.2.0", "Enable/disable automatic switching.\n" +
                "Disable = 0 \n" +
                "Enable = 1"));
    }

    @Override
    public ArrayList<Param> getParams() {
        return params;
    }

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
