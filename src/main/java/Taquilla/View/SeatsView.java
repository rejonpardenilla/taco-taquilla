package Taquilla.View;

import Elements.Show;
import Elements.Zone;
import Taquilla.Auxiliary.SeatState;
import Taquilla.Controller.SeatsController;
import Taquilla.View.Helpers.GUI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;

public class SeatsView {
    private SeatsController seatsController;
    private JFrame window;
    private GUI gui = new GUI(new BorderLayout());
    private ActionListener purchaseSelection, fillSeats;

    public SeatsView(Show show, String type){
        window = new JFrame();
        this.seatsController = new SeatsController(show, type);
        generateEvents();

        JSplitPane header = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,showInfoBar(show), seatList());
        gui.add(header, BorderLayout.NORTH);
        gui.add(seatsController.generateGridWithEvent(fillSeats), BorderLayout.CENTER);

        window.add(gui);
        window.setSize(new Dimension(1000, 800));
        window.pack();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    private void generateEvents() {
        //Nos lleva a la pantall de venta y registro de cliente
        purchaseSelection = event->{
            new PurchaseView(seatsController.getModified());
            window.dispose();
        };
        fillSeats = event -> fillSeatList();
    }

    private JPanel seatList() {
        JPanel seatContainer = new JPanel(new BorderLayout(4,4));
        seatContainer.setBorder(new TitledBorder("Selected Seats"));

        JPanel seatList = new JPanel(new GridLayout(2,6,0,0));

        JButton proceed = new JButton("Proceed to Purchase");
        proceed.addActionListener(purchaseSelection);

        gui.add("proceedButton", proceed);
        gui.add("seatList", seatList);
        seatContainer.add(seatList);
        seatContainer.add(proceed, BorderLayout.SOUTH);
        seatContainer.setMaximumSize(new Dimension(200, 250));
        gui.add("seatContainer", seatContainer);
        return seatContainer;
    }

    private void fillSeatList(){
        JPanel seatList = (JPanel)gui.$("seatList");
        seatList.removeAll();

        //calculate prices and total
        BigDecimal total = new BigDecimal(0);
        for (SeatState state : seatsController.getModified()){
            System.out.println(state.getSeat().toString());
            BigDecimal cost = seatsController.getDiscountedPrice(state.getSeat().getZone());
            total = total.add(cost);
            seatList.add(new JLabel(state.getSeat().toString() + " - " + cost));
        }
        ((JButton)gui.$("proceedButton")).setText("Proceed to Purchase -> Total = " + total.toString());
        ((JPanel)gui.$("seatContainer")).add(seatList);
        gui.$("seatContainer").validate();
        gui.$("seatContainer").repaint();
        window.pack();
    }

    private JPanel showInfoBar(Show show){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        String showInfo = show.toString();
        panel.setBorder(new TitledBorder(showInfo));

        ArrayList<Zone> zones = seatsController.getZoneList();

        JPanel zonaDiamante = seatsController.generatePriceTag(zones.get(0),new Color(4,176,69));
        JPanel zonaOro = seatsController.generatePriceTag(zones.get(1),new Color(253,247,9));
        JPanel zonaPlata = seatsController.generatePriceTag(zones.get(2),new Color(86,166,218));
        JPanel zonaCobre = seatsController.generatePriceTag(zones.get(3),new Color(250,215,108));
        JPanel zonaLata = seatsController.generatePriceTag(zones.get(4),new Color(232,232,232));

        panel.add(zonaDiamante);
        panel.add(zonaOro);
        panel.add(zonaPlata);
        panel.add(zonaCobre);
        panel.add(zonaLata);
        return panel;
    }
}
