package ru.rtrn.ui;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import ru.rtrn.entity.Device;
import ru.rtrn.entity.LesRadio;
import ru.rtrn.entity.Param;
import ru.rtrn.entity.Station;
import ru.rtrn.repository.StationRepository;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;


public class RemoteControl extends JFrame {
    private JTextField getValue;
    private JButton getButton;
    private JTextField setValue;
    private JTextArea description;
    private JButton setButton;
    private JLabel currentLabel;
    private JLabel newLabel;
    private JScrollPane params;
    private JScrollPane devices;
    private JList object_list;
    private JPanel mainWindow;
    private JList device_list;
    private JScrollPane objects;
    private JList param_list;
    private Station selectedStation;
    private Device selectedDevice;
    private Param selectedParam;
    private String selectedStationName;
    private String selectedDeviceName;
    private String selectedParamName;
    StationRepository stationRepository = new StationRepository();
    DefaultListModel<String> stationDefaultListModel = new DefaultListModel<>();
    DefaultListModel<String> deviceDefaultListModel = new DefaultListModel<>();
    DefaultListModel<String> paramsDefaultListModel = new DefaultListModel<>();

    public RemoteControl() throws SQLException {
        this.setContentPane(mainWindow);
        this.setTitle("Remote Control");
        this.setSize(800, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        stationDefaultListModel.addAll(stationRepository.getNames());
        object_list.setModel(stationDefaultListModel);

        object_list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                clearForm();
                paramsDefaultListModel.clear();
                deviceDefaultListModel.clear();
                selectedStationName = object_list.getSelectedValue().toString();
                selectedStation = stationRepository.getStationByName(selectedStationName);
                if (selectedStation.getDevices() != null) {
                    deviceDefaultListModel.addAll(selectedStation.getDevices());
                    device_list.setModel(deviceDefaultListModel);
                }
            }
        });
        device_list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                clearForm();
                if (device_list.getSelectedValue() != null) {
                    selectedDeviceName = device_list.getSelectedValue().toString();
                    paramsDefaultListModel.clear();

                    if (selectedDeviceName.equals("LesRadio")) {
                        selectedDevice = new LesRadio();
                    }
//                if (selectedDeviceName.equals("TSE800")){
//                    selectedDevice = new TSE800();
//                }
                    paramsDefaultListModel.addAll(selectedDevice.getParams().stream().map(Param::getName).toList());
                    param_list.setModel(paramsDefaultListModel);
                }
            }
        });
        param_list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                clearForm();
                if (param_list.getSelectedValue() != null) {
                    selectedParamName = param_list.getSelectedValue().toString();
                    selectedParam = selectedDevice.getParamByName(selectedParamName);
                    description.setText(selectedParam.getDecription());
                }
            }
        });
        getButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Snmp snmp4j = new Snmp(new DefaultUdpTransportMapping());
                    snmp4j.listen();
                    Address address = new UdpAddress(selectedStation.getIp() + "/" + selectedDevice.getPort());
                    CommunityTarget target = new CommunityTarget();
                    target.setCommunity(new OctetString(selectedDevice.getCommunity()));
                    target.setAddress(address);
                    target.setRetries(2);
                    target.setVersion(SnmpConstants.version2c);
                    PDU request = new PDU();
                    request.setType(PDU.GET);
                    OID oid = new OID(selectedParam.getOid());
                    request.add(new VariableBinding(oid));
                    PDU responsePDU = null;
                    ResponseEvent responseEvent;
                    responseEvent = snmp4j.send(request, target);
                    responsePDU = responseEvent.getResponse();
                    getValue.setText(responsePDU.getVariable(oid).toString());
                } catch (IOException ee) {
                    ee.printStackTrace();
                }
            }
        });
    }

    private void clearForm() {
        getValue.setText("");
        setValue.setText("");
        description.setText("");
    }


    public static void main(String[] args) throws SQLException {
        JFrame frame = new RemoteControl();
    }


}
