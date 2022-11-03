package ru.rtrn.entity;

import org.snmp4j.smi.Variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Uaxte extends Device{
    private static final String type = "Uaxte";
    private static final String community = "private";
    private String port = "8048";
    private ArrayList<Param> params;

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
        Map<String,String> refSource = new HashMap<>();
        refSource.put("1", "Internal");
        refSource.put("2", "External");
        refSource.put("3", "Manual");
        refSource.put("4", "Automatic");
        params.add(new Param("Reference source", ".1.3.6.1.4.1.43768.3.1.1.3.2.13.1.1.0", "OCXO System Reference. \n" +
                "gps(1),\n" +
                "euaxternalREF(2),\n" +
                "manual(3),\n" +
                "auto1PPS(4)" , refSource));
//        Map<String,String> intExtRef = new HashMap<>();
//        intExtRef.put("1", "Internal");
//        intExtRef.put("2", "External");
//        params.add(new Param("Int/Ext Reference", ".1.3.6.1.4.1.43768.3.1.1.3.2.13.1.5.0", "FTR 1PPS actual selection\n" +
//                "Only valid when uaxteOcxoReferenceSystem =Auto1PPS \n" +
//                "internalGPS(1),\n" +
//                "externalGPS(2)", intExtRef));
        Map<String,String> refOutput = new HashMap<>();
        refOutput.put("1", "None");
        refOutput.put("2", "1PPS from GPS");
        refOutput.put("3", "Manual 1PPS");
        refOutput.put("4", "10 MHz");
        params.add(new Param("Reference output", ".1.3.6.1.4.1.43768.3.1.1.3.2.13.1.9.0", "Reference output selection. \n" +
                "none(1),\n" +
                "gps1PPS(2),\n" +
                "synthesized1PPS(3),\n" +
                "tenMHz(4)", refOutput));
        params.add(new Param("FTR Holdover", ".1.3.6.1.4.1.43768.3.1.1.3.2.13.2.0", "Frequency timing reference status, Readiness of the exciter..."));
        params.add(new Param("Number of Satellites", ".1.3.6.1.4.1.43768.3.1.1.3.2.14.1.0", "The number of satellites detected."));
        Map<String,String> curGpsGnssSource = new HashMap<>();
        curGpsGnssSource.put("1", "Primary");
        curGpsGnssSource.put("2", "Secondary");
        params.add(new Param("Current GPS/GNSS source", ".1.3.6.1.4.1.43768.3.1.1.3.2.14.8.0", "Current selection for Global Navigation Satellite System.\n" +
                "Possible choices are Primary or Secondary.\n" +
                "primary(1),\n" +
                "secondary(2)",curGpsGnssSource));
        Map<String,String> primaryGpsGnssSource = new HashMap<>();
        primaryGpsGnssSource.put("1", "GPS");
        primaryGpsGnssSource.put("2", "GLONASS");
        params.add(new Param("Primary GPS/GLONASS source", ".1.3.6.1.4.1.43768.3.1.1.3.2.14.9.0", "Primary Global Navigation Satellite System. Selection of U.S.\n" +
                "GPS or the Russian GLONASS system is possible.\n" +
                "gps(1),\n" +
                "glonass(2)",primaryGpsGnssSource));
        Map<String,String> secondaryGpsGnssSource = new HashMap<>();
        secondaryGpsGnssSource.put("1", "GPS");
        secondaryGpsGnssSource.put("2", "GLONASS");
        params.add(new Param("Secondary GPS/GLONASS source", ".1.3.6.1.4.1.43768.3.1.1.3.2.14.10.0", "Primary Global Navigation Satellite System. Selection of U.S.\n" +
                "GPS or the Russian GLONASS system is possible.\n" +
                "gps(1),\n" +
                "glonass(2)",secondaryGpsGnssSource));
        params.add(new Param("Gps/Gnss automatic", ".1.3.6.1.4.1.43768.3.1.1.3.2.14.11.0", "Switch mode for Global Navigation Satellite System selection.\n" +
                "Possible choices are Auto or Manual.\n" +
                "manual(1),\n" +
                "auto(2)"));
        params.add(new Param("Forward power, mW", ".1.3.6.1.4.1.43768.3.1.1.3.2.7.1.3.0", "Forward power of the system."));
        params.add(new Param("Reflected power, mW", ".1.3.6.1.4.1.43768.3.1.1.3.2.7.1.4.0", "Reflected power of the system."));
        Map<String,String> outputMute = new HashMap<>();
        outputMute.put("1", "Mute");
        outputMute.put("2", "Unmute");
        params.add(new Param("Output Mute", ".1.3.6.1.4.1.43768.3.1.1.3.2.7.2.4.0", "Output power of the system is muted.\n" +
                "muteActive(1),\n" +
                "muteInactive(2)",outputMute));
        Map<String,String> refMuteOnLoss = new HashMap<>();
        refMuteOnLoss.put("1", "No mute");
        refMuteOnLoss.put("2", "Mute immediatly");
        refMuteOnLoss.put("3", "Guard interval exceeds 20%");
        refMuteOnLoss.put("4", "Time out exceeded");
        params.add(new Param("Mute on loss reference", ".1.3.6.1.4.1.43768.3.1.1.3.2.13.1.10.0", " NOTE:this only applies for DVB-T and DVB-T2\n" +
                "noMute(1),\n" +
                "muteImmediatly(2),\n" +
                "guardIntervalExceeds20Percent(3),\n" +
                "timeoutExceeded(4)",refMuteOnLoss));
        Map<String,String> inputMuteOnLoss = new HashMap<>();
        inputMuteOnLoss.put("1", "Mute");
        inputMuteOnLoss.put("2", "No mute");
        params.add(new Param("Mute on loss of TS", ".1.3.6.1.4.1.43768.3.1.1.3.2.5.5.0", "Mute RF when TS stream is lost \n" +
                "yes(1),\n" +
                "no(2)",inputMuteOnLoss));
        Map<String,String> activeInput = new HashMap<>();
        activeInput.put("1", "Primary");
        activeInput.put("2", "Secondary");
        params.add(new Param("Input active", ".1.3.6.1.4.1.43768.3.1.1.3.2.5.2.0", "Active input of the transmitter \n" +
                "primary(1),\n" +
                "secondary(2)",activeInput));
        Map<String,String> inputAuto = new HashMap<>();
        inputAuto.put("1", "Automatic");
        inputAuto.put("2", "Manual");
        inputAuto.put("3", "Auto-return");
        params.add(new Param("Input automatic", ".1.3.6.1.4.1.43768.3.1.1.3.2.5.6.0", "Input selection mode. \n" +
                "automatic(1),\n" +
                "manual(2),\n" +
                "autoReturn(3)", inputAuto));
        Map<String,String> input1status = new HashMap<>();
        input1status.put("1", "Ok");
        input1status.put("2", "Missing");
        input1status.put("3", "Error");
        input1status.put("4", "None");
        params.add(new Param("Input-1 status", ".1.3.6.1.4.1.43768.3.1.1.3.2.5.3.0", "Status of the primary input of the transmitter. \n" +
                "ok(1),\n" +
                "missing(2),\n" +
                "error(3),\n" +
                "none(4)", input1status));
        Map<String,String> input2status = new HashMap<>();
        input2status.put("1", "Ok");
        input2status.put("2", "Missing");
        input2status.put("3", "Error");
        input2status.put("4", "None");
        params.add(new Param("Input-2 status", ".1.3.6.1.4.1.43768.3.1.1.3.2.5.4.0", "Status of the secondary input of the transmitter. \n" +
                "ok(1),\n" +
                "missing(2),\n" +
                "error(3),\n" +
                "none(4)", input2status));
        params.add(new Param("Frequency", ".1.3.6.1.4.1.290.9.2.1.1.5.4.3.0", "Transmitter frequency."));
        Map<String,String> sfnMode = new HashMap<>();
        sfnMode.put("1", "SFN");
        sfnMode.put("2", "MFN");
        params.add(new Param("SFN/MFN Mode", ".1.3.6.1.4.1.43768.2.1.1.3.2.4.3.1.0", "SFN/MFN mode. \n" +
                "sfn(1),\n" +
                "mfn(2)", sfnMode));
        params.add(new Param("Network Id", ".1.3.6.1.4.1.43768.2.1.1.3.2.4.1.1.6.1", "Uniquely identifies the current DVB network."));
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

