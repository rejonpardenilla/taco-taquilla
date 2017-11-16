package Taquilla.View;

import DataAccess.Implementations.PurchaseDao;
import Elements.Person;
import Elements.Purchase;
import Taquilla.View.Helpers.GUI;
import Taquilla.View.Helpers.JFrameHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class PurchaseHistoryView {
    GUI gui;

    public PurchaseHistoryView() {
        JFrame frame = JFrameHelper.createFrame();
        gui = JFrameHelper.createCenteredGUI("Purchase History");

        JTable table = createTable();
        gui.add("table", table);

        JFrameHelper.addCancelButton(frame, gui, "Back");

        JFrameHelper.showFrameAndGui(frame, gui);
    }

    public static void main(String[] args) {
        new PurchaseHistoryView();
    }

    private static JTable createTable() {
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);

        model.addColumn("Client");
        model.addColumn("Date");
        model.addColumn("Time");
        model.addColumn("Total");

        model.addRow(new String[]{"CLIENT", "DATE", "TIME", "TOTAL"});

        PurchaseDao purchaseDao = new PurchaseDao();
        ArrayList<Purchase> purchases = new ArrayList<>(purchaseDao.findAll());

        for (Purchase purchase : purchases) {
            String[] results = new String[4];

            //Client
            Person client = purchase.getClient();
            String clientName = client.getName() + " " + client.getLastName();
            results[0] = clientName;

            // Date
            String date = purchase.getDate().toString();
            results[1] = date;

            // Time
            String time = purchase.getTime().toString();
            results[2] = time;

            // Total
            String total = purchase.getTotal().toString();
            results[3] = total;

            model.addRow(results);
        }

        return table;
    }
}
