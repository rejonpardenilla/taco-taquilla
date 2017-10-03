package Taquilla.View;

import Elements.Show;
import Taquilla.Model.PurchaseModel;
import Taquilla.View.Helpers.PanelFactory;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PurchaseView extends JPanel implements ActionListener {
    JPanel menu;

    PanelFactory panelFactory;

    PurchaseModel purchaseModel;

    TextField seatRowField, seatNumberField, clientNameField;
    JComboBox<Show> shows;

    void initFields(){
        seatRowField = new TextField();
        seatNumberField = new TextField();
        clientNameField = new TextField();
        panelFactory = new PanelFactory();

        purchaseModel = new PurchaseModel();

//        seatRowField.setColumns(15);
//        seatNumberField.setColumns(15);
//        clientNameField.setColumns(15);
    }

    public void createComboBox(){
        Show[] showArray = (Show[]) purchaseModel.loadShows().toArray(new Show[0]);
        shows = new JComboBox<Show>(showArray);
        shows.setSelectedIndex(0);
        shows.addActionListener(this);
    }

    public void generateLayout(){
        add(shows, BorderLayout.PAGE_START);
        add(menu, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }

    private void createMenu(){
        JPanel row, number, client;
        row = panelFactory.labeledField("Row", seatRowField);
        number = panelFactory.labeledField("Number", seatNumberField);
        client = panelFactory.labeledField("Client name", clientNameField);

        menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.PAGE_AXIS));


        menu.add(row);
        menu.add(number);
        menu.add(client);
        menu.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));

//        menu.setPreferredSize(new Dimension(177, 122+10));
    }

    public PurchaseView() {
        super(new BorderLayout());

        initFields();

        createComboBox();

        createMenu();

        createPurchaseButton();

        generateLayout();
    }

    private void createPurchaseButton() {

    }

    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        seatRowField.setText("action");
        seatNumberField.setText("performed");
    }


    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("PurchaseView");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new PurchaseView();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
