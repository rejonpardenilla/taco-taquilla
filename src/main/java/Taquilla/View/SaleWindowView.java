package Taquilla.View;

import DataAccess.Implementations.SeatingDao;
import DataAccess.Implementations.ShowDao;
import Elements.Play;
import Elements.Seating;
import Elements.Show;
import Taquilla.Controller.SaleWindowController;
import Taquilla.View.Helpers.GUI;
import Taquilla.View.Helpers.JFrameHelper;
import Taquilla.View.Helpers.PanelFactory;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.List;

public class SaleWindowView {
    //Windows and panels
    private JFrame window;
    private GUI gui;

    //Events
    private ActionListener onPlaySelect, onPurchase, onReservation, onReclaim;
    private ListSelectionListener onShowSelect;
    //Controller
    private SaleWindowController saleWindowController;

    private SaleWindowView(){
        init();
    }

    private void init(){
        saleWindowController = new SaleWindowController();
        window = JFrameHelper.createFrame();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setTitle("Taco Taquilla (TM)");

        createEvents();
        generateGUI();

        window.setContentPane(gui);
        window.pack();
        window.setVisible(true);
    }

    private void createEvents(){
        onPlaySelect = event -> {
            ((JTable)gui.$("showTable")).clearSelection();
            fillShowList((Play)gui.getCurrentValueFrom("playsComboBox"));
            gui.$("purchaseButton").setEnabled(false);
            gui.$("reservationButton").setEnabled(false);
            gui.$("reclaimButton").setEnabled(false);
        };

        onShowSelect = event -> {
            gui.$("purchaseButton").setEnabled(true);
            gui.$("reservationButton").setEnabled(true);
            gui.$("reclaimButton").setEnabled(true);
        };

        onReservation = event->{
            ShowDao showDao = new ShowDao();
            Show currentShow = showDao.findById((int)gui.getCurrentValueFrom("showTable"));
            if (currentShow.getTime().getHour()-2 < LocalTime.now().getHour())
                JOptionPane.showMessageDialog(gui, "Cannot make reservations for this show anymore!");
            else
                new SeatsView(currentShow, "RESERVED");
        };

        onReclaim = event -> {
            ShowDao showDao = new ShowDao();
            Show currentShow = showDao.findById((int)gui.getCurrentValueFrom("showTable"));
            if (currentShow.getTime().getHour()-2 < LocalTime.now().getHour())
                JOptionPane.showMessageDialog(gui, "Too late for reservations!!");
            else {
                SeatingDao seatingDao = new SeatingDao();
                List<Seating> seatings = seatingDao.findByShow(currentShow);
                if (seatings.size() > 0) {
                    JFrame list = new JFrame();
                    for (Seating seating : seatings) {
                        if (seating.getState().toLowerCase().equals("reserved"))
                            list.add(new JLabel(seating.getSeat().toString()));
                    }
                    list.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(gui, "No reservations on this show!");
                }
            }
        };

        onPurchase = event->{
            ShowDao showDao = new ShowDao();
            Show currentShow = showDao.findById((int)gui.getCurrentValueFrom("showTable"));
            new SeatsView(currentShow, "TAKEN");
        };

    }

    private void generateGUI() {
        gui = new GUI(new BorderLayout(5,5));
        gui.setBorder(new TitledBorder("Taco Taquilla GUI"));

        gui.add(generatePlaySelector(), BorderLayout.NORTH);
        gui.add(generateButtonSidebar(), BorderLayout.EAST);
        gui.add("showList", generateShowList(), BorderLayout.CENTER);
    }

    private JPanel generatePlaySelector() {
        JPanel playSelector = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        playSelector.setBorder(new TitledBorder("Select Play to display shows"));

        //combobox
        Play[] plays = saleWindowController.getPlays().toArray(new Play[0]);
        JComboBox playsComboBox = (JComboBox) gui.add("playsComboBox", new JComboBox<Play>(plays));

        //Set selected to first show, or show a message if none are found
        if (plays.length < 1) playsComboBox.addItem(new Play("No plays found. Check db"));

        playsComboBox.addActionListener(onPlaySelect);
        playSelector.add(playsComboBox);

        return playSelector;
    }

    private JScrollPane generateShowList() {
        JTable showTable = (JTable) gui.add("showTable", new JTable());
        fillShowList(new Play());
        showTable.setAutoCreateRowSorter(true);
        JScrollPane showList = new JScrollPane(showTable);
        Dimension tableDimension = showList.getPreferredSize();
        showList.setPreferredSize(
                new Dimension(tableDimension.width, tableDimension.height/3));

        showTable.getSelectionModel().addListSelectionListener(onShowSelect);


        return showList;
    }

    private void fillShowList(Play play){
        TableModel tableModel = saleWindowController.getShows(play);
        JTable showTable = (JTable) gui.$("showTable");
        showTable.setModel(tableModel);
        showTable.getColumnModel().getColumn(0).setMaxWidth(10);
    }

    private JPanel generateButtonSidebar() {
        JPanel sidebar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        sidebar.setBorder(new TitledBorder("Proceed to:"));
        JButton purchaseButton = (JButton)gui.add("purchaseButton", new JButton("  Purchase  "));
        purchaseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton reservationButton = (JButton)gui.add("reservationButton", new JButton("Reservation"));
        reservationButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton reclaimReservation =  (JButton)gui.add("reclaimButton", new JButton("  Reclaim  "));

        purchaseButton.addActionListener(onPurchase);
        reservationButton.addActionListener(onReservation);
        reclaimReservation.addActionListener(onReclaim);

        purchaseButton.setEnabled(false);
        reservationButton.setEnabled(false);
        reclaimReservation.setEnabled(false);

        sidebar.add(purchaseButton);
        sidebar.add(reservationButton);
        sidebar.add(reclaimReservation);

        sidebar.setPreferredSize(new Dimension(130,200));
        return sidebar;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SaleWindowView::new);
    }

}
