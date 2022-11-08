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
public class Sx801Form extends JFrame{
    private JPanel sx801;
    private JTextField rfld;
    private JTextField fwd;
    private JTextField vswr;
    private JTextField sumFault;
    private JTextField sumWarning;
    private JTextField rfOutput;
    private JTextField frequency;
    Station station;
    Device device;
    String port;

    public Sx801Form (Station station, Device device, String port) {
        this.station = station;
        this.device = device;
        this.port = port;
        this.setContentPane(sx801);
        this.setTitle("Sx801");
        this.setSize(300, 600);
        this.setVisible(true);
        initForm();
    }
    private void initForm() {
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
            oids.add(new OID(device.getParamByName("Frequency").getOid()));
            oids.add(new OID(device.getParamByName("Current forward power").getOid()));
            oids.add(new OID(device.getParamByName("Current reflected power").getOid()));
            oids.add(new OID(device.getParamByName("Summary fault").getOid()));
            oids.add(new OID(device.getParamByName("Summary warning").getOid()));
            oids.add(new OID(device.getParamByName("RF status").getOid()));

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
            Integer calcFreq = Integer.parseInt(values.get(0))/1000000;
            frequency.setText(calcFreq.toString());
            fwd.setText(values.get(1));
            rfld.setText(values.get(2));
            Double calcFwd = Double.parseDouble(values.get(1));
            Double calcRlfd = Double.parseDouble(values.get(2));
            Double calcVswr = (1+Math.sqrt(calcRlfd/calcFwd))/(1-Math.sqrt(calcRlfd/calcFwd));
            vswr.setText(String.format("%.2f",calcVswr));
            sumFault.setText(device.getParamByName("Summary fault").getValues().get(values.get(3)));
            sumWarning.setText(device.getParamByName("Summary warning").getValues().get(values.get(4)));
            rfOutput.setText(device.getParamByName("RF status").getValues().get(values.get(5)));
            snmp.close();
        } catch (IOException ee) {
            ee.printStackTrace();
        }
    }
}