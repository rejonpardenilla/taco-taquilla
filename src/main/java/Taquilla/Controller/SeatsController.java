package Taquilla.Controller;

import DataAccess.Implementations.ShowDao;
import Elements.Seat;
import Elements.Show;
import Elements.Zone;
import Taquilla.Auxiliary.SeatButton;
import Taquilla.Auxiliary.SeatState;
import Taquilla.Model.SeatsModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SeatsController {
    SeatsModel seatsModel = new SeatsModel();
    SeatState[][] seatGrid;
    private ArrayList<SeatState> modified = new ArrayList<>();

    String type;
    Show show;

    /**
     * Contains the underlying state of the seat view
     * @param type purchase, reservation, preorder
     * @param show the show
     */
    public SeatsController(Show show, String type){
        this.type = type;
        this.show = show;
    }

    public JPanel generateGridWithEvent(ActionListener actionListener){
        seatGrid = seatsModel.generateGrid(show);
        JPanel panel = new JPanel(new GridLayout(8,20));
        for (int i = 0; i < seatGrid.length; i++){
            for(int j = 0; j < seatGrid[0].length; j++){
                SeatState seatState = seatGrid[i][j];
                SeatButton sb = new SeatButton(seatState);

                sb.addActionListener(actionListener);
                //Se agrega el estado al array modified
                sb.addActionListener(event->{
                    SeatState selfState = sb.getSeatState();
                    if (!sb.isSelected()){
                        selfState.modifySeatState(this.type, this.show);
                        modified.add(selfState);
                    } else {
                        modified.remove(selfState);
                    }
                });
                panel.add(sb);
            }
        }
        return panel;
    }

    public BigDecimal getDiscountedPrice(Zone zone){
        BigDecimal price = show.getPrice();
        BigDecimal discountPercent = BigDecimal.valueOf(zone.getDiscountPercent());
        return (price.multiply(discountPercent.divide(BigDecimal.valueOf(100))));
    }

    public JPanel generatePriceTag(Zone zone, Color color){
        JPanel priceTag = new JPanel();
        String discount = BigDecimal.valueOf(zone.getDiscountPercent()).toString();
        priceTag.add(new JLabel(zone.getName() + "  -  " + discount));
        priceTag.setAlignmentX(Component.LEFT_ALIGNMENT);
        priceTag.setMaximumSize(new Dimension(200,50));
        priceTag.setBackground(color);
        return priceTag;
    }

    public String getType() {
        return type;
    }

    public Show getShow() {
        return show;
    }

    public ArrayList<SeatState> getModified() {
        return modified;
    }

    public ArrayList<Zone> getZoneList(){
        ArrayList<Zone> allZones =  new ArrayList<>(seatsModel.getAllZones());
        allZones.sort(Comparator.comparingInt(Zone::getId));
        return allZones;
    }



}
