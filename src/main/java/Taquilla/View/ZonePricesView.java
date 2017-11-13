package Taquilla.View;

import DataAccess.Implementations.ZoneDao;
import Elements.Zone;
import Taquilla.View.Helpers.GUI;
import Taquilla.View.Helpers.JFrameHelper;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class ZonePricesView {
    public GUI gui;
    ZoneDao zoneDao = new ZoneDao();

    public ZonePricesView() {
        JFrame frame = JFrameHelper.createFrame();
        gui = JFrameHelper.createCenteredGUI("Establish Zone Prices");

        ArrayList<Zone> zones = new ArrayList<>(zoneDao.findAll());

        // Existing Zones
        for (Zone zone : zones) {
            JTextField textArea = new JTextField();
            String textAreaId = "textArea" + zone.getId();
            textArea.setColumns(5);
            textArea.setText(Integer.toString(zone.getDiscountPercent()));
            gui.addLabeledField(textAreaId, zone.getName(), textArea);
        }

        JFrameHelper.addSeparator(gui, 20);

        // Add new Zone name
        JTextField nameZoneField = new JTextField();
        nameZoneField.setColumns(10);
        gui.addLabeledField("newZoneNameField", "Name: ", nameZoneField);

        // Add new Zone discount
        JTextField discountZoneField = new JTextField();
        discountZoneField.setColumns(5);
        gui.addLabeledField("discountZoneField", "Discount: ", discountZoneField);

        JToggleButton submitZoneButton = new JToggleButton("Submit");
        submitZoneButton.addActionListener(actionEvent -> {
            Zone zone = new Zone();
            zone.setName((String) gui.getCurrentValueFrom("newZoneNameField"));
            zone.setDiscountPercent(Integer.parseInt((String) gui.getCurrentValueFrom("discountZoneField")));
            try {
                zone.save();
                JFrameHelper.showMessageAndClose(frame, submitZoneButton, "Zone added!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        gui.add("submitZoneButton", submitZoneButton);

        JFrameHelper.showFrameAndGui(frame, gui);
    }

    public static void main(String[] args) {
        new ZonePricesView();
    }
}
