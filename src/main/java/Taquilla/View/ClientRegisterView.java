package Taquilla.View;

import Elements.Person;
import Taquilla.View.Helpers.GUI;
import Taquilla.View.Helpers.JFrameHelper;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class ClientRegisterView {
    public GUI gui;

    public ClientRegisterView() {
        JFrame frame = JFrameHelper.createFrame();
        gui = new GUI(new FlowLayout(FlowLayout.CENTER), "Register Client");

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
                JFrameHelper.showMessageAndClose(frame, sendButton, "Client saved!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        gui.add(sendButton);

        frame.setContentPane(gui);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ClientRegisterView();
    }
}
