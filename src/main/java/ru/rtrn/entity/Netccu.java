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
        params.add(new Param("ResetAllFaults", ".1.3.6.1.4.1.2566.127.1.2.167.200.1.1.0", "Reset all faults (in all modules). \n" +
                "idle(1),\n" +
                "trigger(2)"));
        params.add(new Param("OperationModeAutomatic", ".1.3.6.1.4.1.2566.127.1.2.167.200.1.20.1.0", "This switches the automatic switching-over of transmitters on or off. \n" +
                "on(1),\n" +
                "off(2)"));
        params.add(new Param("SelectedTxToDummyLoad", ".1.3.6.1.4.1.2566.127.1.2.167.200.1.20.4.0", "The preselected transmitter to dummy load. \n" +
                "transmitterB(1),\n" +
                "transmitterA1(2),\n" +
                "transmitterA2(3)"));
        params.add(new Param("LoadedTx", ".1.3.6.1.4.1.2566.127.1.2.167.200.1.20.13.0", "The actual loaded Tx. \n" +
                "undefined(0),\n" +
                "transmitterB(1),\n" +
                "transmitterA1(2),\n" +
                "transmitterA2(3)"));
        params.add(new Param("Restart", ".1.3.6.1.4.1.2566.127.1.2.167.1.1.3.5.1.0", "Restart the software of transmitter's control unit. \n" +
                "idle(1),\n" +
                "trigger(2)"));
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
