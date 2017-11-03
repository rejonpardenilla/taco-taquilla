package Taquilla.View;

import Elements.Show;
import Taquilla.Controller.PurchaseController;
import Taquilla.View.Helpers.PanelFactory;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@Deprecated //UNFINISHED
public class PurchaseView extends JPanel implements ActionListener {
    //Panel Factory and panels
    private PanelFactory panelFactory;
    private JPanel menu;

    //Components
    private TextField seatRowField, seatNumberField, clientNameField;
    private JComboBox<Show> shows;

    //Controller
    private PurchaseController purchaseController;

    public PurchaseView() {
        super(new BorderLayout());

        initFields();
        createComboBox();
        createMenu();
        createPurchaseButton();
        generateLayout();
    }

    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        seatRowField.setText("action");
        seatNumberField.setText("performed");
    }

    void initFields(){
        seatRowField = new TextField();
        seatNumberField = new TextField();
        clientNameField = new TextField();
        panelFactory = new PanelFactory();

        purchaseController = new PurchaseController();
    }

    public void createComboBox(){
        Show[] showArray = (Show[]) purchaseController.loadShows().toArray(new Show[0]);
        shows = new JComboBox<Show>(showArray);
        shows.setSelectedIndex(0);
        shows.addActionListener(this);
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

    private void createPurchaseButton() {
        //TODO
    }

    public void generateLayout(){
        add(shows, BorderLayout.PAGE_START);
        add(menu, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame window = new JFrame("PurchaseView");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new PurchaseView();
        newContentPane.setOpaque(true);
        window.setContentPane(newContentPane);

        //Display the window.
        window.pack();
        window.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
