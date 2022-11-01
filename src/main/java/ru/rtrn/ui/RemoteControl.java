package ru.rtrn.ui;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import ru.rtrn.entity.*;
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
    private JLabel getValueLabel;
    private JLabel setValueLabel;
    private JScrollPane params;
    private JScrollPane devices;
    private JList object_list;
    private JPanel mainWindow;
    private JList device_list;
    private JScrollPane objects;
    private JList param_list;
    private JTextField port;
    private JLabel portLabel;
    private Station selectedStation;
    private Device selectedDevice;
    private Param selectedParam;
    private String selectedStationName;
    private String selectedDeviceName;
    private String selectedParamName;
    private String variableType;
    private Variable var;
    StationRepository stationRepository = new StationRepository();
    DefaultListModel<String> stationDefaultListModel = new DefaultListModel<>();
    DefaultListModel<String> deviceDefaultListModel = new DefaultListModel<>();
    DefaultListModel<String> paramsDefaultListModel = new DefaultListModel<>();

    public RemoteControl() throws SQLException {
        this.setContentPane(mainWindow);
        this.setTitle("Remote Control");
        this.setSize(1000, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        stationDefaultListModel.addAll(stationRepository.getNames());
        object_list.setModel(stationDefaultListModel);

        object_list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                clearForm();
                port.setText("");
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
                port.setText("");
                if (device_list.getSelectedValue() != null) {
                    selectedDeviceName = device_list.getSelectedValue().toString();
                    paramsDefaultListModel.clear();

                    if (selectedDeviceName.equals("LesRadio")) {
                        selectedDevice = new LesRadio();
                    }
                    if (selectedDeviceName.equals("Tse800")) {
                        selectedDevice = new Tse800();
                    }
                    if (selectedDeviceName.equals("Sx801")) {
                        selectedDevice = new Sx801();
                    }
                    if (selectedDeviceName.equals("Uaxte")) {
                        selectedDevice = new Uaxte();
                    }
                    if (selectedDeviceName.equals("Uaxte1")) {
                        selectedDevice = new Uaxte("8047");
                    }
                    if (selectedDeviceName.equals("Uaxte2")) {
                        selectedDevice = new Uaxte("8048");
                    }
                    if (selectedDeviceName.equals("Netccu")) {
                        selectedDevice = new Netccu();
                    }
                    if (selectedDeviceName.equals("Tx9")) {
                        selectedDevice = new Tx9();
                    }
                    if (selectedDeviceName.equals("Vigintos")) {
                        selectedDevice = new Vigintos();
                    }
                    if (selectedDeviceName.equals("Tse800B")) {
                        selectedDevice = new Tse800("8041");
                    }
                    if (selectedDeviceName.equals("Sx801B")) {
                        selectedDevice = new Sx801("8042");
                    }
                    if (selectedDeviceName.equals("Tse800A2")) {
                        selectedDevice = new Tse800("8045");
                    }
                    if (selectedDeviceName.equals("Sx801A2")) {
                        selectedDevice = new Sx801("8048");
                    }

                    paramsDefaultListModel.addAll(selectedDevice.getParams().stream().map(Param::getName).toList());
                    param_list.setModel(paramsDefaultListModel);
                    port.setText(selectedDevice.getPort());
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
                setValue.setText("");
                if ((selectedStation != null) && (selectedDevice != null) && (selectedParam != null)) {
                    try {
                        TransportMapping transport = new DefaultUdpTransportMapping();
                        transport.listen();
                        Address address = new UdpAddress(selectedStation.getIp() + "/" + port.getText());
                        CommunityTarget target = new CommunityTarget();
                        target.setCommunity(new OctetString(selectedDevice.getCommunity()));
                        target.setAddress(address);
                        target.setRetries(2);
                        target.setTimeout(1000);
                        target.setVersion(SnmpConstants.version2c);
                        PDU request = new PDU();
                        request.setType(PDU.GET);
                        OID oid = new OID(selectedParam.getOid());
                        request.add(new VariableBinding(oid));
                        PDU responsePDU = null;
                        ResponseEvent responseEvent;
                        Snmp snmp = new Snmp(transport);
                        responseEvent = snmp.send(request, target);
                        if (responseEvent != null) {
                            responsePDU = responseEvent.getResponse();
                            if (responsePDU != null) {
                                int errorStatus = responsePDU.getErrorStatus();
                                String errorStatusText = responsePDU.getErrorStatusText();
                                if (errorStatus == PDU.noError) {
                                    variableType = responsePDU.getVariable(oid).getClass().getSimpleName();
                                    getValue.setText(responsePDU.getVariable(oid).toString());
                                } else {
                                    getValue.setText(errorStatusText);
                                }
                            } else {
                                getValue.setText("Error: Response PDU is null");
                            }
                        } else {
                            getValue.setText("Error: Agent Timeout... ");
                        }
                        snmp.close();
                    } catch (IOException ee) {
                        description.setText(ee.getMessage());
                        ee.printStackTrace();
                    }
                }
            }
        });
        setButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!setValue.getText().equals("") && (selectedStation != null) && (selectedDevice != null) && (selectedParam != null) && (variableType != null)) {
                    try {
                        TransportMapping transport = new DefaultUdpTransportMapping();
                        transport.listen();
                        Address address = new UdpAddress(selectedStation.getIp() + "/" + port.getText());
                        CommunityTarget target = new CommunityTarget();
                        target.setCommunity(new OctetString(selectedDevice.getCommunity()));
                        target.setAddress(address);
                        target.setRetries(2);
                        target.setTimeout(1000);
                        target.setVersion(SnmpConstants.version2c);
                        PDU request = new PDU();
                        OID oid = new OID(selectedParam.getOid());
                        if (variableType.equals("Integer32")) {
                            var = new Integer32(Integer.parseInt(setValue.getText()));
                        }
                        if (variableType.equals("OctetString")) {
                            var = new OctetString(setValue.getText());
                        }
                        VariableBinding varBind = new VariableBinding(oid, var);
                        request.add(varBind);
                        request.setType(PDU.SET);
                        Snmp snmp = new Snmp(transport);
                        ResponseEvent response = snmp.set(request, target);
                        if (response != null) {
                            PDU responsePDU = response.getResponse();
                            if (responsePDU != null) {
                                int errorStatus = responsePDU.getErrorStatus();
                                String errorStatusText = responsePDU.getErrorStatusText();
                                if (errorStatus == PDU.noError) {
                                    getValue.setText(responsePDU.getVariable(oid).toString());
                                    setValue.setText("SUCCESS");
                                } else {
                                    setValue.setText(errorStatusText);
                                }
                            } else {
                                setValue.setText("Error: Response PDU is null");
                            }
                        } else {
                            setValue.setText("Error: Agent Timeout... ");
                        }
                        snmp.close();
                    } catch (IOException ee) {
                        description.setText(ee.getMessage());
                        ee.printStackTrace();
                    }
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
