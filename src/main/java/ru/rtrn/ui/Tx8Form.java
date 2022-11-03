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

public class Tx8Form extends JFrame {
    private JPanel tse800;
    private JPanel input;
    private JPanel synchronization;
    private JPanel output;
    private JPanel coder;
    private JTextField inputActive;
    private JTextField inputAuto;
    private JTextField inputStatus1;
    private JTextField refSource;
    private JTextField refCurrent;
    private JTextField refHoldover;
    private JTextField sfnMode;
    private JTextField networkId;
    private JTextField sumFault;
    private JTextField sumWarning;
    private JTextField rfOutput;
    private JTextField inputStatus2;
    Station station;
    Device device;
    String port;

    public Tx8Form(Station station, Device device, String port) {
        this.station = station;
        this.device = device;
        this.port = port;
        this.setContentPane(tse800);
        this.setTitle("TSE800");
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
            oids.add(new OID(device.getParamByName("Input automatic").getOid()));
            oids.add(new OID(device.getParamByName("Input active").getOid()));
            oids.add(new OID(device.getParamByName("Input-1 status").getOid()));
            oids.add(new OID(device.getParamByName("Input-2 status").getOid()));
            oids.add(new OID(device.getParamByName("Reference source").getOid()));
            oids.add(new OID(device.getParamByName("Reference current").getOid()));
            oids.add(new OID(device.getParamByName("Reference holdover").getOid()));
            oids.add(new OID(device.getParamByName("SFN/MFN Mode").getOid()));
            oids.add(new OID(device.getParamByName("Network Id").getOid()));
            oids.add(new OID(device.getParamByName("Summary fault").getOid()));
            oids.add(new OID(device.getParamByName("Summary warning").getOid()));
            oids.add(new OID(device.getParamByName("RF output").getOid()));
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
            inputAuto.setText(device.getParamByName("Input automatic").getValues().get(values.get(0)));
            inputActive.setText(device.getParamByName("Input active").getValues().get(values.get(1)));
            inputStatus1.setText(device.getParamByName("Input-1 status").getValues().get(values.get(2)));
            inputStatus2.setText(device.getParamByName("Input-2 status").getValues().get(values.get(3)));
            refSource.setText(device.getParamByName("Reference source").getValues().get(values.get(4)));
            refCurrent.setText(device.getParamByName("Reference current").getValues().get(values.get(5)));
            refHoldover.setText(device.getParamByName("Reference holdover").getValues().get(values.get(6)));
            sfnMode.setText(device.getParamByName("SFN/MFN Mode").getValues().get(values.get(7)));
            networkId.setText(values.get(8));
            sumFault.setText(device.getParamByName("Summary fault").getValues().get(values.get(9)));
            sumWarning.setText(device.getParamByName("Summary warning").getValues().get(values.get(10)));
            rfOutput.setText(device.getParamByName("RF output").getValues().get(values.get(11)));
            snmp.close();
        } catch (IOException ee) {
            ee.printStackTrace();
        }
    }
}
