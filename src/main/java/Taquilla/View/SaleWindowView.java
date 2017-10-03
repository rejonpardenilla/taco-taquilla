package Taquilla.View;

import Elements.Play;
import Taquilla.Controller.SaleWindowController;

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
    private JPanel gui, seats;

    //Key components
    private JTable showTable;
    private JComboBox<Play> playsComboBox;

    //Events
    private ActionListener onPlaySelect, onSeatSelect, onClearButton;
    private ListSelectionListener onShowSelect;

    //Controller
    private SaleWindowController saleWindowController;

    private SaleWindowView(){
        init();
    }

    private void init(){
        saleWindowController = new SaleWindowController();
        window = new JFrame();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setTitle("Taco Taquilla (TM)");

        createEvents();
        gui = generateGUI();
        window.setContentPane(gui);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setMinimumSize(window.getSize());
        window.setVisible(true);
    }

    private void createEvents(){
        onPlaySelect = event -> fillShowList((Play) playsComboBox.getSelectedItem());

        onShowSelect = event -> { //EVENTO AL HACER CLIC EN UNA FILA DE LA TABLA
            int show_id = Integer.valueOf((String) showTable.getValueAt(showTable.getSelectedRow(),0));
            //TODO: LLAMAR LA PANTALLA DE ASIENTOS DEL SHOW INDICADO
        };

        onSeatSelect = new ActionListener() {//EVENTO AL SELECCIONAR UN ASIENTO
            private int seatCount = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                //aqui se agrega
                seats.add(new JLabel(seatCount + "H-3"));
                seatCount++;
                window.validate();
            }
        };

        onClearButton = event -> {
            //TODO EVENTO DE CLEAR ASIENTOS
        };

    }

    private JPanel generateGUI(){
        JPanel gui = new JPanel(new BorderLayout(5,5));
        gui.setBorder(new TitledBorder("Taco Taquilla GUI"));

        gui.add(generatePlaySelector(), BorderLayout.NORTH);
        gui.add(generateShowList(), BorderLayout.CENTER);
        gui.add(generateSeatList(), BorderLayout.WEST);
        return gui;
    }

    private JPanel generatePlaySelector(){
        JPanel playSelector = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        playSelector.setBorder(new TitledBorder("Select Play to display shows"));

        //combobox
        Play[] plays = saleWindowController.getPlays().toArray(new Play[0]);
        playsComboBox = new JComboBox<Play>(plays);

        //Set selected to first show, or show a message if none are found
        if (plays.length < 1) playsComboBox.addItem(new Play("No plays found. Check db"));
        playsComboBox.setSelectedIndex(0);

        playsComboBox.addActionListener(onPlaySelect);
        playSelector.add(playsComboBox);

        return playSelector;
    }

    private JScrollPane generateShowList() {
        showTable = new JTable();
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
        showTable.setModel(tableModel);
        showTable.getColumnModel().getColumn(0).setMaxWidth(10);
    }

    private JPanel generateSeatList() {
        JPanel seatList = new JPanel(new BorderLayout(4,4));
        seatList.setBorder(new TitledBorder("Selected Seats"));
        seats = new JPanel(new GridLayout(0,2,3,3));
        seats.setBorder(new TitledBorder("hello"));
        seatList.add(seats);
        //CLEAR BUTTON
        JButton clear = new JButton("Clear");
        clear.addActionListener(onSeatSelect); //change event to onClearButton
        seatList.add(clear, BorderLayout.SOUTH);

        seatList.setPreferredSize(new Dimension(100,200));
        return seatList;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SaleWindowView::new);
    }

}
