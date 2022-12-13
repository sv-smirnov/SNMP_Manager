package ru.rtrn.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        params.add(new Param("Output mute", ".1.3.6.1.4.1.2566.127.1.2.216.2.1.2.3.1.0", "Switch used to switch the components in the program path of the\n" +
                "transmitter on and off.\n" +
                "on(1),\n" +
                "off(2)"));
        params.add(new Param("Output power", ".1.3.6.1.4.1.2566.127.1.2.216.3.1.1.2.1.3.1", "The nominal power of the transmitter must be set here."));
        params.add(new Param("Forward power", ".1.3.6.1.4.1.2566.127.1.2.216.3.1.1.3.1.1.1", "Forward power of the transmitter.", false));
        params.add(new Param("Reflected power", ".1.3.6.1.4.1.2566.127.1.2.216.3.1.1.3.1.2.1", "Forward power of the transmitter.", false));
        Map<String,String> refCurrent = new HashMap<>();
        refCurrent.put("1", "Manual");
        refCurrent.put("2", "Ext. 5 MHz");
        refCurrent.put("3", "Ext. 10 MHz");
        refCurrent.put("4", "Ext. 1 PPS");
        refCurrent.put("5", "Internal GPS/GNSS");
        params.add(new Param("Reference current", ".1.3.6.1.4.1.2566.127.1.2.216.3.1.5.2.1.1.1.1", "The current source for frequency synchronization. \n"+
                "manual(1),\n" +
                "external5MHz(2),\n" +
                "external10MHz(3),\n" +
                "externalPPS(4),\n" +
                "internalGPS(5)", refCurrent, false));
        Map<String,String> refSource = new HashMap<>();
        refSource.put("1", "Manual");
        refSource.put("2", "Ext. 5 MHz");
        refSource.put("3", "Ext. 10 MHz");
        refSource.put("4", "Ext. 1 PPS");
        refSource.put("5", "Internal GPS/GNSS");
        refSource.put("6", "Automatic");
        params.add(new Param("Reference source", ".1.3.6.1.4.1.2566.127.1.2.216.3.1.5.3.1.1.1.1", "The appropriate signal source for the reference frequency\n" +
                "may be determined automatically or defined by this value.\n" +
                "manual(1),\n" +
                "external5MHz(2),\n" +
                "external10MHz(3),\n" +
                "externalPPS(4),\n" +
                "internalGPS(5),\n" +
                "auto(6)", refSource));
        Map<String,String> refHoldover = new HashMap<>();
        refHoldover.put("1", "Unknown");
        refHoldover.put("2", "Inaccurate");
        refHoldover.put("3", "Good");
        refHoldover.put("4", "Good for holdover");
        params.add(new Param("Reference holdover", ".1.3.6.1.4.1.2566.127.1.2.216.3.1.5.2.1.2.1.1", "The precision of frequency regulation.\n" +
                "unknown(1),\n" +
                "inaccurate(2),\n" +
                "good(3),\n" +
                "standsGuardTime(4)", refHoldover));
        params.add(new Param("Satellites Used", ".1.3.6.1.4.1.2566.127.1.2.216.2.1.8.2.1.4.1", "Indicates how many satellites are used for calculating the current time.", false));
        Map<String,String> inputAuto = new HashMap<>();
        inputAuto.put("1", "On");
        inputAuto.put("2", "Off");
        params.add(new Param("Input automatic", ".1.3.6.1.4.1.2566.127.1.2.216.3.1.4.2.1.1.1.1", "Switches the input automatic on or off. \n" +
                "on(1),\n" +
                "off(2)", inputAuto));
        Map<String,String> activeInput = new HashMap<>();
        activeInput.put("2", "Input-1");
        activeInput.put("3", "Input-2");
        params.add(new Param("Input active", ".1.3.6.1.4.1.2566.127.1.2.216.3.1.4.2.1.3.1.1", "Used to select the active input if the automatic input signal\n" +
                "switchover function is switched off \n" +
                "Input-1(2),\n" +
                "Input-2(3)",activeInput));
        Map<String,String> input1status = new HashMap<>();
        input1status.put("1", "Undefined");
        input1status.put("2", "Off");
        input1status.put("3", "Fault");
        input1status.put("4", "Warning");
        input1status.put("5", "Ok");
        params.add(new Param("Input-1 status", ".1.3.6.1.4.1.2566.127.1.2.216.3.1.3.1.1.8.1.1.1.3006", "See textual convention for details. \n" +
                "undefined(1),\n" +
                "off(2),\n" +
                "fault(3),\n" +
                "warning(4),\n" +
                "ok(5)", input1status, false));
        Map<String,String> input2status = new HashMap<>();
        input2status.put("1", "Undefined");
        input2status.put("2", "Off");
        input2status.put("3", "Fault");
        input2status.put("4", "Warning");
        input2status.put("5", "Ok");
        params.add(new Param("Input-2 status", ".1.3.6.1.4.1.2566.127.1.2.216.3.1.3.1.1.8.1.1.2.3006", "See textual convention for details. \n" +
                "undefined(1),\n" +
                "off(2),\n" +
                "fault(3),\n" +
                "warning(4),\n" +
                "ok(5)", input2status, false));
        Map<String,String> sfnMode = new HashMap<>();
        sfnMode.put("2", "MFN");
        sfnMode.put("3", "SFN");
        params.add(new Param("SFN/MFN mode", ".1.3.6.1.4.1.2566.127.1.2.216.3.1.6.3.1.2.1.1", "Selects mode of time synchronization:\n" +
                "off(2) - MFN\n" +
                "on(3)  - SFN", sfnMode));
        params.add(new Param("Network Id", ".1.3.6.1.4.1.2566.127.1.2.216.5.1.3.2.1.6.1.1", "The actual network ID for the transmitter.", false));
        params.add(new Param("Frequency", ".1.3.6.1.4.1.2566.127.1.2.216.3.1.2.2.1.1.1.1", "The transmitter frequency."));
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
