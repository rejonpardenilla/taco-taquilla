package Taquilla.View;

import DataAccess.Implementations.PurchaseDao;
import Elements.Purchase;
import Elements.Person;
import Taquilla.View.Helpers.GUI;
import Taquilla.View.Helpers.JFrameHelper;

import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class PurchaseHistoryView {
    GUI gui;
    PurchaseDao purchaseDao = new PurchaseDao();

    public PurchaseHistoryView() {
        JFrame frame = JFrameHelper.createFrame();
        gui = JFrameHelper.createLeftAlignedGUI("Purchase History");

        gui.add(new JTextArea("NAME"));
        JFrameHelper.addSeparator(gui, 20);
        gui.add(new JTextArea("DATE"));
        JFrameHelper.addSeparator(gui, 20);
        gui.add(new JTextArea("TIME"));
        JFrameHelper.addSeparator(gui, 20);
        gui.add(new JTextArea("TOTAL"));
        JFrameHelper.addSeparator(gui, 180);

        ArrayList<Purchase> purchases = new ArrayList<>(purchaseDao.findAll());

        for (Purchase purchase : purchases) {
            // Client name
            Person client = purchase.getClient();
            String clientName = client.getName() + " " + client.getLastName();
            JTextArea nameTextArea = new JTextArea(clientName);
            String nameId = "nameText" + Integer.toString(purchase.getId());
            gui.add(nameId, nameTextArea);
            JFrameHelper.addSeparator(gui, 15);

            // Date
            if (purchase.getDate() == null) break;
            JTextArea dateTextArea = new JTextArea(JFrameHelper.formatDate(purchase.getDate()));
            String dateId = "dateText" + Integer.toString(purchase.getId());
            gui.add(dateId, dateTextArea);
            JFrameHelper.addSeparator(gui, 15);
            JFrameHelper.addSeparator(gui, 100);

            // Time
            // some code {...}

            // Total
            // some code {...}
        }

        JFrameHelper.showFrameAndGui(frame, gui);
    }

    public static void main(String[] args) {
        new PurchaseHistoryView();
    }
}
