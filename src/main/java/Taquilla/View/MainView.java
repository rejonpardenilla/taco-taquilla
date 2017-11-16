package Taquilla.View;

import Taquilla.Controller.EditPlayController;
import Taquilla.Controller.PurchaseController;
import Taquilla.Controller.RegisterPlayController;
import Taquilla.Model.EditPlayModel;
import Taquilla.Model.RegisterPlayModel;
import Taquilla.View.Helpers.GUI;
import Taquilla.View.Helpers.JFrameHelper;
import Taquilla.Views.EditPlayView;
import Taquilla.Views.RegisterPlayView;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import javax.swing.*;
import java.util.EventListener;

public class MainView {
    public GUI gui;

    public MainView() {
        JFrame frame = JFrameHelper.createFrame();
        gui = JFrameHelper.createCenteredGUI("TACO TAQUILLA™ by NapChallenge");

        JButton registerPlaysButton = (JButton) gui.add(new JButton("Register Plays"));
        registerPlaysButton.addActionListener(actionEvent -> new RegisterPlayController(new RegisterPlayView(), new RegisterPlayModel()));

        JButton editPlaysButton = (JButton) gui.add(new JButton("Edit Plays"));
        editPlaysButton.addActionListener(actionEvent -> new EditPlayController(new EditPlayView(), new EditPlayModel()));

        JButton salesButton = (JButton) gui.add(new JButton("Purchase Tickets"));
        salesButton.addActionListener(actionEvent -> {
            // @julian
        });

        JButton purchaseHistoryButton = (JButton) gui.add(new JButton("Purchase History"));
        purchaseHistoryButton.addActionListener(actionEvent -> new PurchaseHistoryView());

        JFrameHelper.showFrameAndGui(frame, gui);
    }

    public static void main(String[] args) {
        new MainView();
    }
}
