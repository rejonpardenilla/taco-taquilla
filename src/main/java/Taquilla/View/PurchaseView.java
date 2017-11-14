package Taquilla.View;

import DataAccess.Implementations.PurchaseDao;
import Elements.Person;
import Elements.Purchase;
import Elements.Ticket;
import Taquilla.Auxiliary.SeatState;
import Taquilla.View.Helpers.GUI;
import Taquilla.View.Helpers.JFrameHelper;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

public class PurchaseView {
    public GUI gui;

    public PurchaseView(ArrayList<SeatState> seats) {
        JFrame frame = JFrameHelper.createFrame();
        frame.setSize(new Dimension(200, 250));

        gui = JFrameHelper.createCenteredGUI("Register Client");
        gui.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Name
        JTextField nameField = new JTextField();
        nameField.setColumns(10);
        gui.addLabeledField("nameField", "Name: ", nameField);

        // Last Name
        JTextField lastNameField = new JTextField();
        lastNameField.setColumns(10);
        gui.addLabeledField("lastNameField", "Last Name: ", lastNameField);

        // Phone
        JTextField phoneField = new JTextField();
        phoneField.setColumns(10);
        gui.addLabeledField("phoneField", "Phone: ", phoneField);

        // Email
        JTextField emailField = new JTextField();
        emailField.setColumns(10);
        gui.addLabeledField("emailField", "Email: ", emailField);

        JToggleButton sendButton = new JToggleButton();
        sendButton.setText("Register");
        sendButton.addActionListener(actionEvent -> {
            Person client = new Person();
            client.setType("client");
            client.setName((String) gui.getCurrentValueFrom("nameField"));
            client.setLastName((String) gui.getCurrentValueFrom("lastNameField"));
            client.setPhone((String) gui.getCurrentValueFrom("phoneField"));
            client.setEmail((String) gui.getCurrentValueFrom("emailField"));
            try {
                client.save();
                proceedToPurchase(client, seats);
//                JFrameHelper.showMessageAndClose(frame, sendButton, "Client saved!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        gui.add(sendButton);

        frame.setContentPane(gui);
        frame.setVisible(true);
    }

    private void proceedToPurchase(Person client, ArrayList<SeatState> seats) {
        Purchase purchase = new Purchase();
        BigDecimal total = new BigDecimal(0);
        for (SeatState state : seats){
            Ticket ticket = new Ticket();
            BigDecimal price = state.getSeating().getShow().getPrice();
            BigDecimal discountPercent = BigDecimal.valueOf(state.getSeat().getZone().getDiscountPercent());
            BigDecimal cost = (price.multiply(discountPercent.divide(BigDecimal.valueOf(100))));
            total = total.add(cost);
        }
        purchase.setTotal(total);
        purchase.setClient(client);

        ArrayList<Ticket> tickets = new ArrayList<>();
        try {
            purchase.save();
            for (SeatState state : seats){
                Ticket ticket = new Ticket();
                BigDecimal price = state.getSeating().getShow().getPrice();
                BigDecimal discountPercent = BigDecimal.valueOf(state.getSeat().getZone().getDiscountPercent());
                BigDecimal cost = (price.multiply(discountPercent.divide(BigDecimal.valueOf(100))));
                ticket.setPurchase(purchase);
                ticket.setSeating(state.getSeating().save());
                ticket.setReturned(false);
                ticket.setPrice(cost);
                tickets.add(ticket.save());
            }
            finalizePurchase(tickets);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void finalizePurchase(ArrayList<Ticket> tickets) {
        JFrame frame = JFrameHelper.createFrame();
        GUI minigui = JFrameHelper.createCenteredGUI("Thank you for your purchase");
        JPanel report = new JPanel();
        report.setLayout(new BoxLayout(report, BoxLayout.Y_AXIS));
        JLabel header = new JLabel("The total for " + tickets.size() + " tickets is: " + tickets.get(0).getPurchase().getTotal().toString());
        header.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
        report.add(header);
        for(Ticket ticket : tickets){
            report.add(new JLabel(ticket.getSeating().getSeat().toString() + " - " + ticket.getPrice()));
        }
        minigui.add(report);
        frame.setContentPane(minigui);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
//        new PurchaseView();
    }
}
