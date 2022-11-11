package ru.rtrn.entity;

import java.util.ArrayList;

public class Netccu extends Device{
    private static final String type = "Netccu";
    private final String community = "broadcast";
    private ArrayList<Param> params;
    private String port = "8047";
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
        params.add(new Param("Reset faults", ".1.3.6.1.4.1.2566.127.1.2.167.200.1.1.0", "Reset all faults (in all modules). \n" +
                "idle(1),\n" +
                "trigger(2)"));
        params.add(new Param("Automatic mode", ".1.3.6.1.4.1.2566.127.1.2.167.200.1.20.1.0", "This switches the automatic switching-over of transmitters on or off. \n" +
                "on(1),\n" +
                "off(2)"));
        params.add(new Param("Tx to load", ".1.3.6.1.4.1.2566.127.1.2.167.200.1.20.4.0", "The preselected transmitter to dummy load. \n" +
                "transmitterB(1),\n" +
                "transmitterA1(2),\n" +
                "transmitterA2(3)"));
        params.add(new Param("Current loaded Tx", ".1.3.6.1.4.1.2566.127.1.2.167.200.1.20.13.0", "The actual loaded Tx. \n" +
                "undefined(0),\n" +
                "transmitterB(1),\n" +
                "transmitterA1(2),\n" +
                "transmitterA2(3)", false));
        params.add(new Param("Restart", ".1.3.6.1.4.1.2566.127.1.2.167.1.1.3.5.1.0", "Restart the software of transmitter's control unit. \n" +
                "idle(1),\n" +
                "trigger(2)"));
        params.add(new Param("Reserve output", ".1.3.6.1.4.1.2566.127.1.2.167.200.1.30.1.1.3.1", "On/Off RF output of Tx on load. \n" +
                "On(1),\n" +
                "Off(2)"));
        params.add(new Param("Mux-1 output", ".1.3.6.1.4.1.2566.127.1.2.167.200.1.30.1.1.3.2", "On/Off MUX-1 RF output. \n" +
                "On(1),\n" +
                "Off(2)"));
        params.add(new Param("Mux-2 output", ".1.3.6.1.4.1.2566.127.1.2.167.200.1.30.1.1.3.3", "On/Off MUX-2 RF output. \n" +
                "On(1),\n" +
                "Off(2)"));
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
