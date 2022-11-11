package ru.rtrn.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        params.add(new Param("Frequency", ".1.3.6.1.4.1.2566.127.1.2.167.212.1.5.1.64.1.1", "This is the RF frequency."));
        params.add(new Param("Current forward power", ".1.3.6.1.4.1.2566.127.1.2.167.201.1.2.1.13.1", "The measured forwarded power [W]..", false));
        params.add(new Param("Current reflected power", ".1.3.6.1.4.1.2566.127.1.2.167.201.1.2.1.14.1", "The measured reflected power [W].", false));
        params.add(new Param("Output power", ".1.3.6.1.4.1.2566.127.1.2.167.212.1.5.1.99.1.1", "Amplifier output power 0 ... 400 W."));
        params.add(new Param("Output mute", ".1.3.6.1.4.1.2566.127.1.2.167.212.1.5.1.70.1.1", "Turns on/switches off the output signal.\n" +
                "This switch takes the same effect as the switch on the frontpanel of the exciter.\n" +
                "on(1),\n" +
                "off(2)"));
        params.add(new Param("Restart", ".1.3.6.1.4.1.2566.127.1.2.167.1.1.3.5.1.0", "Restart the software of transmitter's control unit.\n" +
                "idle(1),\n" +
                "trigger(2)"));
        params.add(new Param("Reset fault", ".1.3.6.1.4.1.2566.127.1.2.167.201.1.1.1.3.1", "Reset all faults (in all modules) of the transmitter.\n" +
                "idle(1),\n" +
                "trigger(2)"));
        Map<String,String> sumWarning = new HashMap<>();
        sumWarning.put("1", "Warning");
        sumWarning.put("2", "Ok");
        params.add(new Param("Summary warning", ".1.3.6.1.4.1.2566.127.1.2.167.201.1.2.1.5.1", "This indicates a summary warning of the transmitter.\n" +
                "Warning(1)\n" +
                "Ok(2)", sumWarning, false));
        Map<String,String> sumFault = new HashMap<>();
        sumFault.put("1", "Fault");
        sumFault.put("2", "Ok");
        params.add(new Param("Summary fault", ".1.3.6.1.4.1.2566.127.1.2.167.201.1.2.1.4.1", "This indicates a summary fault of the transmitter.\n" +
                "Fault(1)\n" +
                "Ok(2)", sumFault, false));
        Map<String,String> rfOutput = new HashMap<>();
        rfOutput.put("1", "Ok");
        rfOutput.put("2", "Mute");
        params.add(new Param("RF status", ".1.3.6.1.4.1.2566.127.1.2.167.201.1.2.1.7.1", "Shows whether RF is present for the corresponding transmitter.\n" +
                "Ok(1),\n" +
                "Mute(2)", rfOutput, false));

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
