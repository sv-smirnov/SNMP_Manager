package ru.rtrn.entity;

import java.util.ArrayList;

public class Tse800 extends Device{
    private static final String type = "Tse800";
    private final String community = "broadcast";
    private ArrayList<Param> params;
    private String port = "8047";

    public Tse800() {
        initParams();
    }

    public Tse800(String port) {
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
        params.add(new Param("GpsGnssSelection", ".1.3.6.1.4.1.2566.127.1.2.167.102.1.1.1.7.2.2", "Chooses the GNSS, which shall be used. \n" +
                "undefined(1),\n" +
                "unknown(2),\n" +
                "none(3),\n" +
                "gps(4),\n" +
                "glonass(5),\n" +
                "gpsAndGlonass(6)"));
        params.add(new Param("StatusSatellitesUsed", ".1.3.6.1.4.1.2566.127.1.2.167.102.1.3.1.1.1.2.2", "Number of satellites currently being used for position and time determination"));
        params.add(new Param("FrqRgCurrentSource", ".1.3.6.1.4.1.2566.127.1.2.167.301.1.2.1.1.0", "Shows the source currently used for frequency synchronization. \n" +
                "manual(1),\n" +
                "external5MHz(2),\n" +
                "external10MHz(3),\n" +
                "external1PPS(4),\n" +
                "internalGPS(5)"));
        params.add(new Param("FrqRgSource", ".1.3.6.1.4.1.2566.127.1.2.167.301.1.2.2.1.0", "Selects the appropriate reference source.\n" +
                "manual(1),\n" +
                "external5MHz(2),\n" +
                "external10MHz(3),\n" +
                "external1PPS(4),\n" +
                "internalGPS(5),\n" +
                "auto(6)"));
        params.add(new Param("Restart", ".1.3.6.1.4.1.2566.127.1.2.167.1.1.3.5.1.0", "Restart the software of transmitter's control unit.\n" +
                "idle(1),\n" +
                "trigger(2)"));
        params.add(new Param("CfgNetworkMode", ".1.3.6.1.4.1.2566.127.1.2.167.212.1.2.1.3.2.1", "Network Mode.\n" +
                "mfn(1),\n" +
                "sfn(2)"));
        params.add(new Param("CmdResetSumFault", ".1.3.6.1.4.1.2566.127.1.2.167.301.1.1.1.0", "Reset all faults (in all modules) of the TSE.\n" +
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
