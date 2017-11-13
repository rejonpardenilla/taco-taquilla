package Taquilla.View;

import Elements.Play;
import Taquilla.Controller.SaleWindowController;
import Taquilla.View.Helpers.GUI;
import Taquilla.View.Helpers.PanelFactory;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaleWindowView {
    //Windows and panels
    private JFrame window;
    private JPanel seats;
    private GUI gui;
    private PanelFactory panelFactory;

    //Key components
//    private JTable showTable;
//    private JComboBox<Play> playsComboBox;
//    JTextField seatRow, seatNumber, clientName, clientLastName, clientPhone;

    //Events
    private ActionListener onPlaySelect, onSeatSelect, onClearButton;
    private ListSelectionListener onShowSelect;

    //Controller
    private SaleWindowController saleWindowController;

    private SaleWindowView(){
        init();
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void init(){
        saleWindowController = new SaleWindowController();
        window = new JFrame();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setTitle("Taco Taquilla (TM)");

        createEvents();
        panelFactory = new PanelFactory();
        generateGUI();
        window.setContentPane(gui);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setMinimumSize(window.getSize());
        window.setVisible(true);
    }

    private void createEvents(){
        onPlaySelect = event -> {
            ((JTable)gui.$("showTable")).clearSelection();
            fillShowList((Play)gui.getCurrentValueFrom("playsComboBox"));
        };
//        onShowSelect = event -> { //EVENTO AL HACER CLIC EN UNA FILA DE LA TABLA
////            int show_id = Integer.valueOf((String) showTable.getValueAt(showTable.getSelectedRow(),0));
//            int show_id = (int) gui.getCurrentValueFrom("showTable");
//            //TODO: LLAMAR LA PANTALLA DE ASIENTOS DEL SHOW INDICADO
//        };

        onSeatSelect = new ActionListener() {//EVENTO AL SELECCIONAR UN ASIENTO
            private int seatCount = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                //aqui se agrega
                seats.add(new JLabel(seatCount + ": " + gui.getCurrentValueFrom("seatRow") + "-" + gui.getCurrentValueFrom("seatNumber")));
                seatCount++;
                window.validate();
            }
        };

        onClearButton = event -> {
            //TODO EVENTO DE CLEAR ASIENTOS
        };

    }

    private void generateGUI() {
        gui = new GUI(new BorderLayout(5,5));
        gui.setBorder(new TitledBorder("Taco Taquilla GUI"));

        gui.add(generatePlaySelector(), BorderLayout.NORTH);
        gui.add(generateShowList(), BorderLayout.CENTER);
        gui.add(generateSeatList(), BorderLayout.WEST);
//        gui.add(generatePurchaseFields(), BorderLayout.CENTER);
//        return gui;
    }

    private JPanel generatePlaySelector() {
        JPanel playSelector = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        playSelector.setBorder(new TitledBorder("Select Play to display shows"));

        //combobox
        Play[] plays = saleWindowController.getPlays().toArray(new Play[0]);
        JComboBox playsComboBox = (JComboBox) gui.add("playsComboBox", new JComboBox<Play>(plays));

        //Set selected to first show, or show a message if none are found
        if (plays.length < 1) playsComboBox.addItem(new Play("No plays found. Check db"));
//        playsComboBox.setSelectedIndex(0);
//        fillShowList(plays[0]);

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

    private JPanel generateSeatList() {
        JPanel seatList = new JPanel(new BorderLayout(4,4));
        seatList.setBorder(new TitledBorder("Selected Seats"));
        seats = new JPanel(new GridLayout(0,1,0,0));
        seats.setBorder(new TitledBorder("hello"));
        seatList.add(seats);
        //CLEAR BUTTON
        JButton clear = new JButton("Clear");
//        clear.addActionListener(onSeatSelect); //change event to onClearButton
        seatList.add(clear, BorderLayout.SOUTH);

        seatList.setPreferredSize(new Dimension(100,200));
        return seatList;
    }

    private JPanel generatePurchaseFields() {
        JPanel seatPanel = new JPanel();
        JTextField seatRow, seatNumber, clientName, clientLastName, clientPhone;
//        seatPanel = panelFactory.titledPanel("Seat:", new BoxLayout(seatPanel, BoxLayout.LINE_AXIS));
        seatPanel = panelFactory.titledPanel("Seat:", new BorderLayout(2,2));
        seatRow = (JTextField) gui.add("seatRow", new JTextField(10));
        seatNumber = (JTextField) gui.add("seatNumber",new JTextField(10));
        clientName = (JTextField) gui.add("clientName",new JTextField(10));
        clientLastName = (JTextField) gui.add("clientLastName",new JTextField(10));
        clientPhone = (JTextField) gui.add("clientPhone",new JTextField(10));

        seatPanel.add(panelFactory.labeledField("Row", seatRow), BorderLayout.NORTH);
        seatPanel.add(panelFactory.labeledField("Number", seatNumber), BorderLayout.CENTER);

        JButton testButton = new JButton("Add seat");
        testButton.addActionListener(onSeatSelect);
        seatPanel.add(testButton, BorderLayout.SOUTH);


        JPanel clientPanel = new JPanel();
//        clientPanel = panelFactory.titledPanel("Client:", new BoxLayout(clientPanel, BoxLayout.LINE_AXIS));
        clientPanel = panelFactory.titledPanel("Client:", new BorderLayout(5,5));
        clientPanel.add(panelFactory.labeledField("Name:", clientName), BorderLayout.NORTH);
        clientPanel.add(panelFactory.labeledField("Last name:", clientLastName), BorderLayout.CENTER);
        clientPanel.add(panelFactory.labeledField("Phone number", clientPhone), BorderLayout.SOUTH);

//        JSplitPane container = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, seatPanel, clientPanel);
        JPanel container = new JPanel(new BorderLayout(4,4));
        container.add(seatPanel, BorderLayout.LINE_START);
        container.add(clientPanel, BorderLayout.LINE_END);
        return container;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SaleWindowView::new);
    }

}
