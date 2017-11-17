package Taquilla.View;

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
    JFrame frame;

    public PurchaseView(ArrayList<SeatState> seats) {
        frame = JFrameHelper.createFrame();
        frame.setSize(new Dimension(400, 300));

        GUI leftPanel;
        leftPanel = JFrameHelper.createCenteredGUI("Register Client");
        leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setSize(new Dimension(400, 300));

        // Name
        JTextField nameField = new JTextField();
        nameField.setColumns(11);
        leftPanel.addLabeledField("nameField", "Name: ", nameField);

        // Last Name
        JTextField lastNameField = new JTextField();
        lastNameField.setColumns(9);
        leftPanel.addLabeledField("lastNameField", "Last Name: ", lastNameField);

        // Phone
        JTextField phoneField = new JTextField();
        phoneField.setColumns(11);
        leftPanel.addLabeledField("phoneField", "Phone: ", phoneField);

        // Email
        JTextField emailField = new JTextField();
        emailField.setColumns(12);
        leftPanel.addLabeledField("emailField", "Email: ", emailField);

        JToggleButton sendButton = new JToggleButton();
        sendButton.setText("Register");
        sendButton.addActionListener(actionEvent -> {
            Person client = new Person();
            client.setType("client");
            client.setName((String) leftPanel.getCurrentValueFrom("nameField"));
            client.setLastName((String) leftPanel.getCurrentValueFrom("lastNameField"));
            client.setPhone((String) leftPanel.getCurrentValueFrom("phoneField"));
            client.setEmail((String) leftPanel.getCurrentValueFrom("emailField"));
            try {
                client.save();
//                frame.dispose();
                proceedToPurchase(client, seats);
//                JFrameHelper.showMessageAndClose(frame, sendButton, "Client saved!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        leftPanel.add(sendButton);
        JButton cancelButton = (JButton) leftPanel.add(new JButton("Cancel"));
        cancelButton.addActionListener(event -> frame.dispose());

        leftPanel.setPreferredSize(new Dimension(200, 300));

        gui = new GUI(new BorderLayout());
        gui.add("left", leftPanel, BorderLayout.WEST);

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
            gui.add(finalizePurchase(tickets), BorderLayout.CENTER);
            gui.revalidate();
            gui.repaint();
            frame.pack();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private GUI finalizePurchase(ArrayList<Ticket> tickets) {
        String type = tickets.get(0).getSeating().getState().toLowerCase().equals("taken")
                        ? "Purchase"
                        : "Reservation";
        GUI minigui = JFrameHelper.createCenteredGUI("Thank you for your " + type);
        JPanel report = new JPanel();
        report.setLayout(new BoxLayout(report, BoxLayout.Y_AXIS));
        JLabel header = new JLabel("The total for " + tickets.size() + " tickets is: " + tickets.get(0).getPurchase().getTotal().toString());
        header.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        report.add(header);
        for(Ticket ticket : tickets){
            report.add(new JLabel(ticket.getSeating().getSeat().toString() + " - " + ticket.getPrice()));
        }
        minigui.add(report);
        return minigui;
    }
}
