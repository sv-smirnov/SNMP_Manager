package ru.rtrn.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Netccu extends Device{
    private static final String type = "Netccu";
    private final String community = "broadcast";
    private ArrayList<Param> params;
    private String port = "8047";
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
        params.add(new Param("Reset faults", ".1.3.6.1.4.1.2566.127.1.2.167.200.1.1.0", "Reset all faults (in all modules). \n" +
                "idle(1),\n" +
                "trigger(2)"));
        params.add(new Param("Automatic mode", ".1.3.6.1.4.1.2566.127.1.2.167.200.1.20.1.0", "This switches the automatic switching-over of transmitters on or off. \n" +
                "on(1),\n" +
                "off(2)"));
        params.add(new Param("Tx to load", ".1.3.6.1.4.1.2566.127.1.2.167.200.1.20.4.0", "The preselected transmitter to dummy load. \n" +
                "transmitterB(1),\n" +
                "transmitterA1(2),\n" +
                "transmitterA2(3)"));
        Map<String,String> loadedTx = new HashMap<>();
        loadedTx.put("0", "Undefined");
        loadedTx.put("1", "Tx B");
        loadedTx.put("2", "Tx A1");
        loadedTx.put("3", "Tx A2");
        params.add(new Param("Current loaded Tx", ".1.3.6.1.4.1.2566.127.1.2.167.200.1.20.13.0", "The actual loaded Tx. \n" +
                "undefined(0),\n" +
                "transmitterB(1),\n" +
                "transmitterA1(2),\n" +
                "transmitterA2(3)",loadedTx, false));
        params.add(new Param("Restart", ".1.3.6.1.4.1.2566.127.1.2.167.1.1.3.5.1.0", "Restart the software of transmitter's control unit. \n" +
                "idle(1),\n" +
                "trigger(2)"));
        params.add(new Param("Reserve output", ".1.3.6.1.4.1.2566.127.1.2.167.200.1.30.1.1.3.1", "On/Off RF output of Tx on load. \n" +
                "On(1),\n" +
                "Off(2)"));
        params.add(new Param("Mux-1 output", ".1.3.6.1.4.1.2566.127.1.2.167.200.1.30.1.1.3.2", "On/Off MUX-1 RF output. \n" +
                "On(1),\n" +
                "Off(2)"));
        params.add(new Param("Mux-2 output", ".1.3.6.1.4.1.2566.127.1.2.167.200.1.30.1.1.3.3", "On/Off MUX-2 RF output. \n" +
                "On(1),\n" +
                "Off(2)"));
        Map<String,String> txBConnection = new HashMap<>();
        txBConnection.put("1", "No connection");
        txBConnection.put("2", "Ok");
        params.add(new Param("TxB connection", ".1.3.6.1.4.1.2566.127.1.2.167.201.1.2.1.3.1", "Connection between NCU and TxB. \n" +
                "No connection(1),\n" +
                "Ok(2)",txBConnection, false));
        Map<String,String> txA1Connection = new HashMap<>();
        txA1Connection.put("1", "No connection");
        txA1Connection.put("2", "Ok");
        params.add(new Param("TxA1 connection", ".1.3.6.1.4.1.2566.127.1.2.167.201.1.2.1.3.2", "Connection between NCU and TxA1. \n" +
                "No connection(1),\n" +
                "Ok(2)",txA1Connection, false));
        Map<String,String> txA2Connection = new HashMap<>();
        txA2Connection.put("1", "No connection");
        txA2Connection.put("2", "Ok");
        params.add(new Param("TxA2 connection", ".1.3.6.1.4.1.2566.127.1.2.167.201.1.2.1.3.3", "Connection between NCU and TxA2. \n" +
                "No connection(1),\n" +
                "Ok(2)",txA2Connection, false));
        Map<String,String> txBFault = new HashMap<>();
        txBFault.put("1", "Fault");
        txBFault.put("2", "Ok");
        params.add(new Param("TxB fault", ".1.3.6.1.4.1.2566.127.1.2.167.201.1.2.1.4.1", "This indicates a summary fault of the TxB. \n" +
                "Fault(1),\n" +
                "Ok(2)",txBFault, false));
        Map<String,String> txA1Fault = new HashMap<>();
        txA1Fault.put("1", "Fault");
        txA1Fault.put("2", "Ok");
        params.add(new Param("TxA1 fault", ".1.3.6.1.4.1.2566.127.1.2.167.201.1.2.1.4.2", "This indicates a summary fault of the TxA1. \n" +
                "Fault(1),\n" +
                "Ok(2)",txA1Fault, false));
        Map<String,String> txA2Fault = new HashMap<>();
        txA2Fault.put("1", "Fault");
        txA2Fault.put("2", "Ok");
        params.add(new Param("TxA2 fault", ".1.3.6.1.4.1.2566.127.1.2.167.201.1.2.1.4.3", "This indicates a summary fault of the TxA2. \n" +
                "Fault(1),\n" +
                "Ok(2)",txA2Fault, false));
        Map<String,String> txBWarning = new HashMap<>();
        txBWarning.put("1", "Warning");
        txBWarning.put("2", "Ok");
        params.add(new Param("TxB warning", ".1.3.6.1.4.1.2566.127.1.2.167.201.1.2.1.5.1", "This indicates a summary warning of the TxB. \n" +
                "Warning(1),\n" +
                "Ok(2)",txBWarning, false));
        Map<String,String> txA1Warning = new HashMap<>();
        txA1Warning.put("1", "Warning");
        txA1Warning.put("2", "Ok");
        params.add(new Param("TxA1 warning", ".1.3.6.1.4.1.2566.127.1.2.167.201.1.2.1.5.2", "This indicates a summary warning of the TxA1. \n" +
                "Warning(1),\n" +
                "Ok(2)",txA1Warning, false));
        Map<String,String> txA2Warning = new HashMap<>();
        txA2Warning.put("1", "Warning");
        txA2Warning.put("2", "Ok");
        params.add(new Param("TxA2 warning", ".1.3.6.1.4.1.2566.127.1.2.167.201.1.2.1.5.3", "This indicates a summary warning of the TxA2. \n" +
                "Warning(1),\n" +
                "Ok(2)",txA2Warning, false));
        Map<String,String> txBLocal = new HashMap<>();
        txBLocal.put("1", "Local");
        txBLocal.put("2", "Remote");
        params.add(new Param("TxB local", ".1.3.6.1.4.1.2566.127.1.2.167.201.1.2.1.6.1", "This indicates the local/remote-mode of the TxB. \n" +
                "Local(1),\n" +
                "Remote(2)",txBLocal, false));
        Map<String,String> txA1Local = new HashMap<>();
        txA1Local.put("1", "Local");
        txA1Local.put("2", "Remote");
        params.add(new Param("TxA1 local", ".1.3.6.1.4.1.2566.127.1.2.167.201.1.2.1.6.2", "This indicates the local/remote-mode of the TxA1. \n" +
                "Local(1),\n" +
                "Remote(2)",txA1Local, false));
        Map<String,String> txA2Local = new HashMap<>();
        txA2Local.put("1", "Local");
        txA2Local.put("2", "Remote");
        params.add(new Param("TxA2 local", ".1.3.6.1.4.1.2566.127.1.2.167.201.1.2.1.6.3", "This indicates the local/remote-mode of the TxA2. \n" +
                "Local(1),\n" +
                "Remote(2)",txA2Local, false));
        Map<String,String> txBRf = new HashMap<>();
        txBRf.put("1", "On");
        txBRf.put("2", "Off");
        params.add(new Param("TxB RF output", ".1.3.6.1.4.1.2566.127.1.2.167.201.1.2.1.7.1", "The RF is present for the corresponding TxB. \n" +
                "On(1),\n" +
                "Off(2)",txBRf, false));
        Map<String,String> txA1Rf = new HashMap<>();
        txA1Rf.put("1", "On");
        txA1Rf.put("2", "Off");
        params.add(new Param("TxA1 RF output", ".1.3.6.1.4.1.2566.127.1.2.167.201.1.2.1.7.2", "The RF is present for the corresponding TxA1. \n" +
                "On(1),\n" +
                "Off(2)",txA1Rf, false));
        Map<String,String> txA2Rf = new HashMap<>();
        txA2Rf.put("1", "On");
        txA2Rf.put("2", "Off");
        params.add(new Param("TxA2 RF output", ".1.3.6.1.4.1.2566.127.1.2.167.201.1.2.1.7.3", "The RF is present for the corresponding TxA2. \n" +
                "On(1),\n" +
                "Off(2)",txA2Rf, false));
        Map<String,String> autoChanged = new HashMap<>();
        autoChanged.put("1", "Switched");
        autoChanged.put("2", "Ok");
        params.add(new Param("Automatic changed", ".1.3.6.1.4.1.2566.127.1.2.167.200.1.20.11.0", "This indicates whether the automatic has switched over automatically. \n" +
                "true(1)  --> switched over\n" +
                "false(2) --> not switched over",autoChanged, false));
        Map<String,String> autoReady = new HashMap<>();
        autoReady.put("1", "Ready");
        autoReady.put("2", "Not ready");
        params.add(new Param("Automatic ready", ".1.3.6.1.4.1.2566.127.1.2.167.200.1.20.10.0", "This indicates the state of the automatic. \n" +
                "true(1)  --> ready\n" +
                "false(2) --> not ready",autoReady, false));

        Map<String,String> sumFault = new HashMap<>();
        sumFault.put("1", "Fault");
        sumFault.put("2", "Ok");
        params.add(new Param("Summary fault", ".1.3.6.1.4.1.2566.127.1.2.167.200.1.2.0", "This indicates a summary fault of the NetCCU. \n" +
                "true(1)  --> summary fault\n" +
                "false(2) --> no summary fault",sumFault, false));
        Map<String,String> sumWarning = new HashMap<>();
        sumWarning.put("1", "Warning");
        sumWarning.put("2", "Ok");
        params.add(new Param("Summary warning", ".1.3.6.1.4.1.2566.127.1.2.167.200.1.3.0", "This indicates a summary warning of the NetCCU. \n" +
                "true(1)  --> summary warning\n" +
                "false(2) --> no summary warning",sumWarning, false));
        Map<String,String> localMode = new HashMap<>();
        localMode.put("1", "Local");
        localMode.put("2", "Remote");
        params.add(new Param("Local mode", ".1.3.6.1.4.1.2566.127.1.2.167.200.1.4.0", "This indicates the local/remote-mode of the NetCCU. \n" +
                "true(1)  --> local\n" +
                "false(2) --> remote",localMode, false));
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
