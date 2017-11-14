package Taquilla.View;

import Taquilla.View.Helpers.GUI;
import Taquilla.View.Helpers.JFrameHelper;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import javax.swing.*;
import java.util.EventListener;

public class MainView {
    public GUI gui;

    public MainView() {
        JFrame frame = JFrameHelper.createFrame();
        gui = JFrameHelper.createCenteredGUI("TACO TAQUILLA by NapChallenge");

        JButton purchaseHistoryButton = (JButton) gui.add(new JButton("Purchase History"));
        purchaseHistoryButton.addActionListener(actionEvent -> {
            new PurchaseHistoryView();
            // onClick -> new PurchaseHistoryView();
        });

        JButton salesButton = (JButton) gui.add(new JButton("Purchase Tickets"));
        purchaseHistoryButton.addActionListener(actionEvent -> {
            // onClick -> new PurchaseHistoryView();
        });

        JFrameHelper.showFrameAndGui(frame, gui);
    }

    public static void main(String[] args) {
        new MainView();
    }
}
