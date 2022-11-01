package ru.rtrn.entity;

import java.util.ArrayList;

public class Uaxte extends Device{
    private static final String type = "Uaxte";
    private final String community = "private";
    private ArrayList<Param> params;
    private String port = "8048";

    public Uaxte() {
        initParams();
    }

    public Uaxte(String port) {
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
        params.add(new Param("UaxteOcxoReferenceSystem", ".1.3.6.1.4.1.43768.3.1.1.3.2.13.1.1.0", "OCXO System Reference. \n" +
                "gps(1),\n" +
                "euaxternalREF(2),\n" +
                "manual(3),\n" +
                "auto1PPS(4)"));
        params.add(new Param("UaxteOcxoReferenceOutput", ".1.3.6.1.4.1.43768.3.1.1.3.2.13.1.9.0", "Reference output selection. \n" +
                "none(1),\n" +
                "gps1PPS(2),\n" +
                "synthesized1PPS(3),\n" +
                "tenMHz(4)"));
        params.add(new Param("UaxteFtrReadiness", ".1.3.6.1.4.1.43768.3.1.1.3.2.13.2.0", "Frequency timing reference status, Readiness of the exciter..."));
        params.add(new Param("UaxteGpsGnssNumberofSatellites", ".1.3.6.1.4.1.43768.3.1.1.3.2.14.1.0", "The number of satellites detected."));
        params.add(new Param("UaxteGpsGnssActive", ".1.3.6.1.4.1.43768.3.1.1.3.2.14.8.0", "Current selection for Global Navigation Satellite System.\n" +
                "Possible choices are Primary or Secondary.\n" +
                "primary(1),\n" +
                "secondary(2)"));
        params.add(new Param("UaxteGpsGnssPrimary", ".1.3.6.1.4.1.43768.3.1.1.3.2.14.9.0", "Primary Global Navigation Satellite System. Selection of U.S.\n" +
                "GPS or the Russian GLONASS system is possible.\n" +
                "gps(1),\n" +
                "glonass(2)"));
        params.add(new Param("UaxteGpsGnssSecondary", ".1.3.6.1.4.1.43768.3.1.1.3.2.14.10.0", "Primary Global Navigation Satellite System. Selection of U.S.\n" +
                "GPS or the Russian GLONASS system is possible.\n" +
                "gps(1),\n" +
                "glonass(2)"));
        params.add(new Param("UaxteGpsGnssSwitchMode", ".1.3.6.1.4.1.43768.3.1.1.3.2.14.11.0", "Switch mode for Global Navigation Satellite System selection.\n" +
                "Possible choices are Auto or Manual.\n" +
                "manual(1),\n" +
                "auto(2)"));
        params.add(new Param("UaxteOutputLpuForward", ".1.3.6.1.4.1.43768.3.1.1.3.2.7.1.1.0", "Forward power of the low power unit(LPU)."));
        params.add(new Param("UaxteOutputMute", ".1.3.6.1.4.1.43768.3.1.1.3.2.7.2.4.0", "Output power of the system is muted.\n" +
                "muteActive(1),\n" +
                "muteInactive(2)"));
        params.add(new Param("UaxteOcxoReferenceLossMute", ".1.3.6.1.4.1.43768.3.1.1.3.2.13.1.10.0", " NOTE:this only applies for DVB-T and DVB-T2\n" +
                "noMute(1),\n" +
                "muteImmediatly(2),\n" +
                "guardIntervalExceeds20Percent(3),\n" +
                "timeoutExceeded(4)"));
        params.add(new Param("UaxteInputMuteOnTsLoss", ".1.3.6.1.4.1.43768.3.1.1.3.2.5.5.0", "Mute RF when TS stream is lost \n" +
                "yes(1),\n" +
                "no(2)"));
        params.add(new Param("UaxteInputActive", ".1.3.6.1.4.1.43768.3.1.1.3.2.5.2.0", "Active input of the transmitter \n" +
                "primary(1),\n" +
                "secondary(2)"));
        params.add(new Param("UaxteInputSwitchMode", ".1.3.6.1.4.1.43768.3.1.1.3.2.5.6.0", "Input selection mode. \n" +
                "automatic(1),\n" +
                "manual(2),\n" +
                "autoReturn(3)"));
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

