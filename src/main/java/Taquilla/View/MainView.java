package Taquilla.View;

import Taquilla.Controller.EditPlayController;
import Taquilla.Controller.RegisterPlayController;
import Taquilla.Model.EditPlayModel;
import Taquilla.Model.RegisterPlayModel;
import Taquilla.View.Helpers.GUI;
import Taquilla.View.Helpers.JFrameHelper;
import Taquilla.Views.EditPlayView;
import Taquilla.Views.RegisterPlayView;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class MainView {
    public GUI gui;

    public MainView() {
        JFrame frame = JFrameHelper.createFrame();
        gui = JFrameHelper.createCenteredGUI("TACO TAQUILLA™ by NapChallenge");

        GUI playsGUI = JFrameHelper.createCenteredGUI("Plays");
        JButton registerPlaysButton = (JButton) playsGUI.add(new JButton("Register Plays"));
        registerPlaysButton.addActionListener(actionEvent ->
                new RegisterPlayController(new RegisterPlayView(), new RegisterPlayModel())
        );

        JButton editPlaysButton = (JButton) playsGUI.add(new JButton("Edit Plays"));
        editPlaysButton.addActionListener(actionEvent ->
                new EditPlayController(new EditPlayView(), new EditPlayModel())
        );

        JButton showsCalendarButton = (JButton) playsGUI.add(new JButton("Show Calendar"));
        showsCalendarButton.addActionListener(actionEvent ->
                new ShowsCalendarView()
        );
        gui.add(playsGUI);

        JFrameHelper.addSeparator(gui, 400);

        GUI salesGUI = JFrameHelper.createCenteredGUI("Sales");
        JButton salesButton = (JButton) salesGUI.add(new JButton("Purchase Tickets"));
        salesButton.addActionListener(actionEvent ->
                new SaleWindowView()
        );

        JButton purchaseHistoryButton = (JButton) salesGUI.add(new JButton("Purchase History"));
        purchaseHistoryButton.addActionListener(actionEvent ->
                new PurchaseHistoryView()
        );
        gui.add(salesGUI);

        JFrameHelper.addSeparator(gui, 400);

        GUI zonesGUI = JFrameHelper.createCenteredGUI("Theatre seats zones");
        JButton editZonesButton = (JButton) zonesGUI.add(new JButton("Add or edit Zones"));
        editZonesButton.addActionListener(actionEvent ->
                new ZoneDiscountsView()
        );
        gui.add(zonesGUI);

        JFrameHelper.addSeparator(gui, 600);
        JFrameHelper.addSeparator(gui, 600);
        JFrameHelper.addSeparator(gui, 600);

        JButton cancelButton = (JButton) gui.add(new JButton("Cancel"));
        cancelButton.addActionListener(actionEvent -> frame.dispose());

        JFrameHelper.showFrameAndGui(frame, gui);
    }

    public static void main(String[] args) {
        new MainView();
    }
}
