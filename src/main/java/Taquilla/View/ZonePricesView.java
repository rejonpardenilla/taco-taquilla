package Taquilla.View;

import Taquilla.View.Helpers.GUI;
import Taquilla.View.Helpers.JFrameHelper;

import javax.swing.*;

public class ZonePricesView {
    public GUI gui;

    public ZonePricesView() {
        JFrame frame = JFrameHelper.createFrame();
        gui = JFrameHelper.createCenteredGUI("Establish Zone Prices");

        JFrameHelper.showFrameAndGui(frame, gui);
    }

    public static void main(String[] args) {
        new ZonePricesView();
    }
}
