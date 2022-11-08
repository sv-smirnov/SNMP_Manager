package ru.rtrn.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        params.add(new Param("Gps/Gnss Selection", ".1.3.6.1.4.1.2566.127.1.2.167.102.1.1.1.7.2.2", "Chooses the GNSS, which shall be used. \n" +
                "undefined(1),\n" +
                "unknown(2),\n" +
                "none(3),\n" +
                "gps(4),\n" +
                "glonass(5),\n" +
                "gpsAndGlonass(6)"));
        params.add(new Param("Satellites Used", ".1.3.6.1.4.1.2566.127.1.2.167.102.1.3.1.1.1.2.2", "Number of satellites currently being used for position and time determination"));
        Map<String,String> refCurrent = new HashMap<>();
        refCurrent.put("1", "Manual");
        refCurrent.put("2", "Ext. 5 MHz");
        refCurrent.put("3", "Ext. 10 MHz");
        refCurrent.put("4", "Ext. 1 PPS");
        refCurrent.put("5", "Internal GPS/GNSS");
        params.add(new Param("Reference current", ".1.3.6.1.4.1.2566.127.1.2.167.301.1.2.1.1.0", "Shows the source currently used for frequency synchronization. \n" +
                "manual(1),\n" +
                "external5MHz(2),\n" +
                "external10MHz(3),\n" +
                "external1PPS(4),\n" +
                "internalGPS(5)",refCurrent));
        Map<String,String> refSource = new HashMap<>();
        refSource.put("1", "Manual");
        refSource.put("2", "Ext. 5 MHz");
        refSource.put("3", "Ext. 10 MHz");
        refSource.put("4", "Ext. 1 PPS");
        refSource.put("5", "Internal GPS/GNSS");
        refSource.put("6", "Automatic");
        params.add(new Param("Reference source", ".1.3.6.1.4.1.2566.127.1.2.167.301.1.2.2.1.0", "Selects the appropriate reference source.\n" +
                "manual(1),\n" +
                "external5MHz(2),\n" +
                "external10MHz(3),\n" +
                "external1PPS(4),\n" +
                "internalGPS(5),\n" +
                "auto(6)", refSource));
        Map<String,String> refHoldover = new HashMap<>();
        refHoldover.put("1", "Unknown");
        refHoldover.put("2", "Inaccurate");
        refHoldover.put("3", "Good");
        refHoldover.put("4", "Good for holdover");
        params.add(new Param("Reference holdover", ".1.3.6.1.4.1.2566.127.1.2.167.301.1.2.1.3.0", "Shows the frequency precision of the controller. \n" +
                "unknown(1),\n" +
                "inaccurate(2),\n" +
                "good(3),\n" +
                "standsGuardTime(4)" ,refHoldover));
        params.add(new Param("Restart", ".1.3.6.1.4.1.2566.127.1.2.167.1.1.3.5.1.0", "Restart the software of transmitter's control unit.\n" +
                "idle(1),\n" +
                "trigger(2)"));
        Map<String,String> sfnMode = new HashMap<>();
        sfnMode.put("1", "MFN");
        sfnMode.put("2", "SFN");
        params.add(new Param("SFN/MFN Mode", ".1.3.6.1.4.1.2566.127.1.2.167.212.1.2.1.3.2.1", "Network Mode.\n" +
                "mfn(1),\n" +
                "sfn(2)", sfnMode));
        params.add(new Param("Network Id", ".1.3.6.1.4.1.2566.127.1.2.167.212.1.4.1.5.2.1", "This ID uniquely identifies the current DVB-T2 network."));
        params.add(new Param("Reset Fault", ".1.3.6.1.4.1.2566.127.1.2.167.301.1.1.1.0", "Reset all faults (in all modules) of the TSE.\n" +
                "idle(1),\n" +
                "trigger(2)"));
        Map<String,String> inputAuto = new HashMap<>();
        inputAuto.put("1", "On");
        inputAuto.put("2", "Off");
        params.add(new Param("Input automatic", ".1.3.6.1.4.1.2566.127.1.2.167.301.1.3.2.2.0", "Activates/deactivates the input automatic.\n" +
                "on(1),\n" +
                "off(2)", inputAuto));
        Map<String,String> inputSource = new HashMap<>();
        inputSource.put("1", "Input 1");
        inputSource.put("2", "Input 2");
        params.add(new Param("Input active", ".1.3.6.1.4.1.2566.127.1.2.167.301.1.3.2.1.0", "Possibility to manually change input.\n" +
                "input1(1),\n" +
                "input2(2)", inputSource));
        Map<String,String> inputStatus1 = new HashMap<>();
        inputStatus1.put("1", "Undefined");
        inputStatus1.put("2", "No Signal");
        inputStatus1.put("3", "Fail");
        inputStatus1.put("4", "Warning");
        inputStatus1.put("5", "Ok");
        params.add(new Param("Input-1 status", ".1.3.6.1.4.1.2566.127.1.2.167.301.1.3.1.1.4.1", "The current state of the input 1.\n" +
                "undefined(1),\n" +
                "noSignal(2),\n" +
                "fail(3),\n" +
                "warning(4),\n" +
                "ok(5)\n", inputStatus1));
        Map<String,String> inputStatus2 = new HashMap<>();
        inputStatus2.put("1", "Undefined");
        inputStatus2.put("2", "No Signal");
        inputStatus2.put("3", "Fail");
        inputStatus2.put("4", "Warning");
        inputStatus2.put("5", "Ok");
        params.add(new Param("Input-2 status", ".1.3.6.1.4.1.2566.127.1.2.167.301.1.3.1.1.4.2", "The current state of the input 2.\n" +
                "undefined(1),\n" +
                "noSignal(2),\n" +
                "fail(3),\n" +
                "warning(4),\n" +
                "ok(5)\n", inputStatus2));
//        params.add(new Param("Date & Time", ".1.3.6.1.4.1.2566.127.1.2.167.1.1.3.1.0", "The date and time for the transmitter."));
        Map<String,String> sumFault = new HashMap<>();
        sumFault.put("1", "Fault");
        sumFault.put("2", "Ok");
        params.add(new Param("Summary fault", ".1.3.6.1.4.1.2566.127.1.2.167.301.3.1.1.5.1", "TSE summary faults\n" +
                "Fault(1),\n" +
                "Ok(2)", sumFault));
        Map<String,String> sumWarning = new HashMap<>();
        sumWarning.put("1", "Warning");
        sumWarning.put("2", "Ok");
        params.add(new Param("Summary warning", ".1.3.6.1.4.1.2566.127.1.2.167.301.3.1.1.5.2", "TSE summary warnings\n" +
                "Warning(1),\n" +
                "Ok(2)", sumWarning));
        Map<String,String> rfOutput = new HashMap<>();
        rfOutput.put("1", "Ok");
        rfOutput.put("2", "Mute");
        params.add(new Param("RF status", ".1.3.6.1.4.1.2566.127.1.2.167.301.3.1.1.5.5", "TSE RF output\n" +
                "Ok(1),\n" +
                "Mute(2)", rfOutput));
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
