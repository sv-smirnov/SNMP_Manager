package ru.rtrn.ui;

import net.percederberg.mibble.MibLoaderException;
import ru.rtrn.entity.Device;
import ru.rtrn.entity.LesRadio;
import ru.rtrn.entity.Station;
import ru.rtrn.repository.StationRepository;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
    private String selectedStationName;
    private Station selectedStation;
    private Device selectedDevice;
    private String selectedDeviceName;
    private String selectedParamName;
    StationRepository stationRepository = new StationRepository();

    public RemoteControl() throws SQLException {
        this.setContentPane(mainWindow);
        this.setTitle("Remote Control");
        this.setSize(600, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DefaultListModel<String> stationDefaultListModel = new DefaultListModel<>();
        DefaultListModel<String> deviceDefaultListModel = new DefaultListModel<>();
        DefaultListModel<String> paramsDefaultListModel = new DefaultListModel<>();
        stationDefaultListModel.addAll(stationRepository.getNames());
        object_list.setModel(stationDefaultListModel);

        object_list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedStationName = object_list.getSelectedValue().toString();
                deviceDefaultListModel.clear();
                selectedStation = stationRepository.getStationByName(selectedStationName);
                if (selectedStation.getDevices() != null) {
                    deviceDefaultListModel.addAll(selectedStation.getDevices());
                device_list.setModel(deviceDefaultListModel);}
            }
        });
        device_list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedDeviceName = device_list.getSelectedValue().toString();
                paramsDefaultListModel.clear();


                if (selectedDeviceName.equals("LesRadio")){
                    try {
                        selectedDevice = new LesRadio();
                    } catch (MibLoaderException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
//                if (selectedDeviceName.equals("TSE800")){
//                    selectedDevice = new TSE800();
//                }
//                if (selectedDeviceName.equals("Sx801")){
//                    selectedDevice = new Sx801();
//                }
                paramsDefaultListModel.addAll(selectedDevice.getParams());
                param_list.setModel(paramsDefaultListModel);

//
//                selectedDeviceName = device_list.getSelectedValue().toString();
//                ArrayList<Device> devices = DeviceRepository.getInstance().getDevicesByStationName(stationName);
//                if (devices != null) {
//                    deviceDefaultListModel.addAll(devices.stream().map(Device::getName).toList());
//                    device_list.setModel(deviceDefaultListModel);}
            }
        });
    }


    public static void main(String[] args) throws SQLException {
        JFrame frame = new RemoteControl();

    }


}
