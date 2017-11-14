package Taquilla.View;

import DataAccess.Implementations.SeatDao;
import DataAccess.Implementations.ShowDao;
import Elements.Seat;
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
//    SeatgenerateSeatMatrix()
    SeatsController seatsController;
    GUI gui = new GUI(new BorderLayout());
    JFrame window;
    public SeatsView(Show show, String type){
        window = new JFrame();
        window.setSize(new Dimension(1000, 800));
        this.seatsController = new SeatsController(show, type);
//        gui.add(showInfoBar(show), BorderLayout.NORTH);
//        gui.add(seatList(), BorderLayout.EAST);
        gui.add(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,showInfoBar(show), seatList()), BorderLayout.NORTH);
        gui.add(seatsController.generateGridWithEvent(e -> fillSeatList()), BorderLayout.CENTER);
        window.add(gui);
        window.pack();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    private JPanel seatList() {
        JPanel seatContainer = new JPanel(new BorderLayout(4,4));
        seatContainer.setBorder(new TitledBorder("Selected Seats"));
        JPanel seatList = new JPanel(new GridLayout(2,6,0,0));
        JButton proceed = new JButton("Proceed to Purchase");
        proceed.addActionListener(e->{
            new PurchaseView(seatsController.getModified());
            window.dispose();
        });
        gui.add("proceedButton", proceed);
        gui.add("seatList", seatList);
        seatContainer.add(seatList);
        seatContainer.add(proceed, BorderLayout.SOUTH);
        seatContainer.setMaximumSize(new Dimension(200, 250));
        gui.add("seatContainer", seatContainer);
        return seatContainer;
    }

    private void fillSeatList(){
        System.out.println("w");
        System.out.println(seatsController.getModified().size());
        JPanel seatList = (JPanel)gui.$("seatList");
        seatList.removeAll();
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

    public static void main(String[] args) {
        ShowDao showDao = new ShowDao();
        new SeatsView(showDao.findById(1), "TAKEN");
    }
}
