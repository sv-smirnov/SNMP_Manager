package ru.rtrn.ui;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import ru.rtrn.entity.Device;
import ru.rtrn.entity.Station;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class NetccuForm extends JFrame{
    private JPanel netccuForm;
    private JTextField txA1SumWarn;
    private JTextField txBSumWarn;
    private JTextField txBConnection;
    private JTextField txA1Connection;
    private JTextField txA2Connection;
    private JTextField txBSumFault;
    private JTextField txA1SumFault;
    private JTextField txA2SumFault;
    private JTextField txA2SumWarn;
    private JTextField txBLocal;
    private JTextField txA1Local;
    private JTextField txA2Local;
    private JTextField txBRf;
    private JTextField txA1Rf;
    private JTextField txA2Rf;
    private JTextField loadedTx;
    private JTextField autoChanged;
    private JTextField autoReady;
    private JTextField sumFault;
    private JTextField sumWarning;
    private JTextField localMode;
    Station station;
    Device device;
    String port;

    public NetccuForm(Station station, Device device, String port) {
        this.station = station;
        this.device = device;
        this.port = port;
        this.setContentPane(netccuForm);
        this.setTitle("NetCCU");
        this.setSize(300, 600);
        this.setVisible(true);
        initForm();
    }

    private void initForm(){
        try {
            TransportMapping transport = new DefaultUdpTransportMapping();
            transport.listen();
            Snmp snmp = new Snmp(transport);
            Address address = new UdpAddress(station.getIp() + "/" + port);
            CommunityTarget target = new CommunityTarget();
            target.setCommunity(new OctetString(device.getCommunity()));
            target.setAddress(address);
            target.setRetries(3);
            target.setTimeout(3000);
            target.setVersion(SnmpConstants.version2c);
            PDU request = new PDU();
            request.setType(PDU.GET);
            ArrayList<OID> oids = new ArrayList<>();

            oids.add(new OID(device.getParamByName("TxB connection").getOid()));
            oids.add(new OID(device.getParamByName("TxA1 connection").getOid()));
            oids.add(new OID(device.getParamByName("TxA2 connection").getOid()));
            oids.add(new OID(device.getParamByName("TxB fault").getOid()));
            oids.add(new OID(device.getParamByName("TxA1 fault").getOid()));
            oids.add(new OID(device.getParamByName("TxA2 fault").getOid()));
            oids.add(new OID(device.getParamByName("TxB warning").getOid()));
            oids.add(new OID(device.getParamByName("TxA1 warning").getOid()));
            oids.add(new OID(device.getParamByName("TxA2 warning").getOid()));
            oids.add(new OID(device.getParamByName("TxB local").getOid()));
            oids.add(new OID(device.getParamByName("TxA1 local").getOid()));
            oids.add(new OID(device.getParamByName("TxA2 local").getOid()));
            oids.add(new OID(device.getParamByName("TxB RF output").getOid()));
            oids.add(new OID(device.getParamByName("TxA1 RF output").getOid()));
            oids.add(new OID(device.getParamByName("TxA2 RF output").getOid()));
            oids.add(new OID(device.getParamByName("Current loaded Tx").getOid()));
            oids.add(new OID(device.getParamByName("Automatic changed").getOid()));
            oids.add(new OID(device.getParamByName("Automatic ready").getOid()));
            oids.add(new OID(device.getParamByName("Summary fault").getOid()));
            oids.add(new OID(device.getParamByName("Summary warning").getOid()));
            oids.add(new OID(device.getParamByName("Local mode").getOid()));

            ArrayList<String> values = new ArrayList<>();
            for (int i = 0; i < oids.size(); i++) {
                request.add(new VariableBinding(oids.get(i)));
                ResponseEvent responseEvent = snmp.send(request, target);
                if (responseEvent != null) {
                    PDU responsePDU = responseEvent.getResponse();
                    if (responsePDU != null) {
                        int errorStatus = responsePDU.getErrorStatus();
                        String errorStatusText = responsePDU.getErrorStatusText();
                        if (errorStatus == PDU.noError) {
                            values.add(responsePDU.getVariable(oids.get(i)).toString());
                        } else {
                            values.add(errorStatusText);
                        }
                    } else {
                        values.add("Error: Response PDU is null");
                    }
                } else {
                    values.add("Error: Agent Timeout... ");
                }
            }
            txBConnection.setText(device.getParamByName("TxB connection").getValues().get(values.get(0)));
            txA1Connection.setText(device.getParamByName("TxA1 connection").getValues().get(values.get(1)));
            txA2Connection.setText(device.getParamByName("TxA2 connection").getValues().get(values.get(2)));
            txBSumFault.setText(device.getParamByName("TxB fault").getValues().get(values.get(3)));
            txA1SumFault.setText(device.getParamByName("TxA1 fault").getValues().get(values.get(4)));
            txA2SumFault.setText(device.getParamByName("TxA2 fault").getValues().get(values.get(5)));
            txBSumWarn.setText(device.getParamByName("TxB warning").getValues().get(values.get(6)));
            txA1SumWarn.setText(device.getParamByName("TxA1 warning").getValues().get(values.get(7)));
            txA2SumWarn.setText(device.getParamByName("TxA2 warning").getValues().get(values.get(8)));
            txBLocal.setText(device.getParamByName("TxB local").getValues().get(values.get(9)));
            txA1Local.setText(device.getParamByName("TxA1 local").getValues().get(values.get(10)));
            txA2Local.setText(device.getParamByName("TxA2 local").getValues().get(values.get(11)));
            txBRf.setText(device.getParamByName("TxB RF output").getValues().get(values.get(12)));
            txA1Rf.setText(device.getParamByName("TxA1 RF output").getValues().get(values.get(13)));
            txA2Rf.setText(device.getParamByName("TxA2 RF output").getValues().get(values.get(14)));
            loadedTx.setText(device.getParamByName("Current loaded Tx").getValues().get(values.get(15)));
            autoChanged.setText(device.getParamByName("Automatic changed").getValues().get(values.get(16)));
            autoReady.setText(device.getParamByName("Automatic ready").getValues().get(values.get(17)));
            sumFault.setText(device.getParamByName("Summary fault").getValues().get(values.get(18)));
            sumWarning.setText(device.getParamByName("Summary warning").getValues().get(values.get(19)));
            localMode.setText(device.getParamByName("Local mode").getValues().get(values.get(20)));

            snmp.close();
        } catch (IOException ee) {
            ee.printStackTrace();
        }
    }
}
