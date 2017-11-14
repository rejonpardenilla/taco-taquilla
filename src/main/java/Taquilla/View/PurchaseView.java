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

public class ClientRegisterView {
    public GUI gui;

    public ClientRegisterView(ArrayList<SeatState> seats) {
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
        PurchaseDao purchaseDao = new PurchaseDao();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ClientRegisterView();
    }
}
