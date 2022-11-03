package ru.rtrn.entity;

import java.util.ArrayList;

public class Sx801 extends Device{
    private static final String type = "Sx801";
    private final String community = "broadcast";
    private ArrayList<Param> params;
    private String port = "8046";
    public Sx801() {
        initParams();
    }

    public Sx801(String port) {
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
        params.add(new Param("StatusTxForwardPower", ".1.3.6.1.4.1.2566.127.1.2.167.201.1.2.1.13.1", "The measured forwarded power [W]."));
        params.add(new Param("StatusTxReflectedPower", ".1.3.6.1.4.1.2566.127.1.2.167.201.1.2.1.14.1", "The measured reflected power [W] (not available for lowpower)."));
        params.add(new Param("CmdExcOutputPower", ".1.3.6.1.4.1.2566.127.1.2.167.212.1.5.1.99.1.1", "Amplifier output power 0 ... 400 W."));
        params.add(new Param("CmdExcRfOutput", ".1.3.6.1.4.1.2566.127.1.2.167.212.1.5.1.70.1.1", "Turns on/switches off the output signal.\n" +
                "This switch takes the same effect as the switch on the frontpanel of the exciter.\n" +
                "on(1),\n" +
                "off(2)"));
        params.add(new Param("Restart", ".1.3.6.1.4.1.2566.127.1.2.167.1.1.3.5.1.0", "Restart the software of transmitter's control unit.\n" +
                "idle(1),\n" +
                "trigger(2)"));
        params.add(new Param("CmdTxResetSumFault", ".1.3.6.1.4.1.2566.127.1.2.167.201.1.1.1.3.1", "Reset all faults (in all modules) of the transmitter.\n" +
                "idle(1),\n" +
                "trigger(2)"));
//        params.add(new Param("Date & Time", ".1.3.6.1.4.1.2566.127.1.2.167.1.1.3.1.0", "The date and time for the transmitter."));
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
