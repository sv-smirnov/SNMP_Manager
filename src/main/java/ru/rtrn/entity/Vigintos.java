package ru.rtrn.entity;

import java.util.ArrayList;

public class Vigintos extends Device{
    private static final String type = "Vigintos";
    private final String community = "private";
    private ArrayList<Param> params;
    private String port = "8047";

    public Vigintos() {
        initParams();
    }

    public Vigintos(String port) {
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
        params.add(new Param("SystemRebootDefaultConfig", ".1.3.6.1.4.1.18086.3082.1.19.0", "Restore all parameters to factory default and reboot the device on the same image. \n" +
                "keeprunning(0),\n"+
                "reboot(1)"));
        params.add(new Param("InputRefSource", ".1.3.6.1.4.1.18086.3082.3.2.0", "Defines the input reference synchronisation source. \n" +
                "auto(0), ext(1), int(2), gps(3)"));
        params.add(new Param("OutputMute", ".1.3.6.1.4.1.18086.3082.4.7.0", "Output mute. \n" +
                "off(0), on(1)"));
        params.add(new Param("Gps1PPSStatus", ".1.3.6.1.4.1.18086.3082.7.1.0", "Returns the current locking state of the GPS system. \n" +
                "locked(0), unlocked(1)"));
        params.add(new Param("GpsTrackedSatellites", ".1.3.6.1.4.1.18086.3082.7.3.0", "Returns the number of currently tracked satellites."));
        params.add(new Param("InputASIAutoRoutingPolicy", ".1.3.6.1.4.1.18086.3082.3.14.0", "The policy to use for TS auto routing. \n" +
                "only-use-primary(0),\n"+
                "only-use-secondary(1),\n" +
                "use-primary-if-available(2),\n"+
                "use-any-available(3)"));
        params.add(new Param("ModeT2MILocalNetworkMode", ".1.3.6.1.4.1.18086.3082.2.14.0", "In Input Mode B:Effective Network Mode. \n" +
                "mfn(0), sfn(1)"));
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

