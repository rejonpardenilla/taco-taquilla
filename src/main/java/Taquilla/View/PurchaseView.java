package Taquilla.View;

import DataAccess.Implementations.ShowDao;
import Elements.Show;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class PurchaseView extends JPanel implements ActionListener {
    JLabel purchaseMenu;

    ShowDao showDao;

    public PurchaseView() {
        super(new BorderLayout());
        ArrayList<Show> shows = new ArrayList<>();
        showDao = new ShowDao();
        shows = (ArrayList<Show>) showDao.findAll();

        JComboBox showList = new JComboBox(shows.toArray());
        showList.setSelectedIndex(0);
        showList.addActionListener(this);

        purchaseMenu = new JLabel();
        purchaseMenu.setHorizontalAlignment(JLabel.CENTER);
        updateLabel(shows.get(0).toString());
        purchaseMenu.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));

        purchaseMenu.setPreferredSize(new Dimension(177, 122+10));

        add(showList, BorderLayout.PAGE_START);
        add(purchaseMenu, BorderLayout.PAGE_END);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }

    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        updateLabel(cb.getSelectedItem().toString());
        purchaseMenu.setText(cb.getSelectedItem().toString());
    }

    protected void updateLabel(String name) {
        purchaseMenu.setText(name);
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
