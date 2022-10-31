package ru.rtrn.entity;

import java.util.ArrayList;

public class Sx801 extends Device{
    private static final String type = "Sx801";
    private final String community = "broadcast";
    private ArrayList<Param> params;
    private String port = "8046";
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
        params.add(new Param("StatusTxForwardPower", ".1.3.6.1.4.1.2566.127.1.2.167.201.1.2.1.13.1", "The measured forwarded power [W]."));
        params.add(new Param("CmdExcOutputPower", ".1.3.6.1.4.1.2566.127.1.2.167.212.1.5.1.99.1.1", "Amplifier output power 0 ... 400 W."));
        params.add(new Param("CmdExcRfOutput", ".1.3.6.1.4.1.2566.127.1.2.167.212.1.5.1.70.1.1", "Turns on/switches off the output signal.\n" +
                "This switch takes the same effect as the switch on the frontpanel of the exciter.\n" +
                "on(1),\n" +
                "off(2)"));
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
