package ru.rtrn.entity;

import java.util.ArrayList;

public class Tx9 extends Device {
    private static final String type = "Tx9";
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
        params.add(new Param("Reboot", ".1.3.6.1.4.1.2566.127.1.2.216.2.1.2.3.1.0", "Reboots the R&S TCE900.\n" +
                "idle(1),\n" +
                "trigger(2)"));
        params.add(new Param("TxTransCmdOperationMode", ".1.3.6.1.4.1.2566.127.1.2.216.2.1.2.3.1.0", "Switch used to switch the components in the program path of the\n" +
                "transmitter on and off.\n" +
                "on(1),\n" +
                "off(2)"));
        params.add(new Param("TxTransCmdNominalPower", ".1.3.6.1.4.1.2566.127.1.2.216.3.1.1.2.1.3.1", "The nominal power of the transmitter must be set here."));
        params.add(new Param("TxTransStForwardPower", ".1.3.6.1.4.1.2566.127.1.2.216.3.1.1.3.1.1.1", "Forward power of the transmitter."));
        params.add(new Param("TxSfnSetupSFN", ".1.3.6.1.4.1.2566.127.1.2.216.3.1.6.3.1.2.1.1", "Selects mode of time synchronization:\n" +
                "undefined(1),\n" +
                "off(2),\n" +
                "on(3)"));
        params.add(new Param("TxFrqRegStateCurrentSource", ".1.3.6.1.4.1.2566.127.1.2.216.3.1.5.2.1.1.1.1", "The current source for frequency synchronization."));
        params.add(new Param("TxFrqRegSetupSource", ".1.3.6.1.4.1.2566.127.1.2.216.3.1.6.3.1.2.1.1", "The appropriate signal source for the reference frequency\n" +
                "may be determined automatically or defined by this value.\n" +
                "manual(1),\n" +
                "external5MHz(2),\n" +
                "external10MHz(3),\n" +
                "externalPPS(4),\n" +
                "internalGPS(5),\n" +
                "auto(6)"));
        params.add(new Param("CommonGpsStUsedSatellites", ".1.3.6.1.4.1.2566.127.1.2.216.2.1.8.2.1.4.1", "Indicates how many satellites are used for calculating the current time."));
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
