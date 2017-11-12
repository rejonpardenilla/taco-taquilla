package Taquilla.View;

import Taquilla.View.Helpers.GUI;
import Taquilla.View.Helpers.JFrameFactory;

import javax.swing.*;
import java.awt.*;

public class ClientRegisterView {
    public GUI gui;

    public ClientRegisterView() {
        JFrame frame = JFrameFactory.createFrame();
        gui = new GUI(new FlowLayout(FlowLayout.CENTER), "Register Client");
        frame.setContentPane(gui);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        ClientRegisterView g = new ClientRegisterView();
    }
}
