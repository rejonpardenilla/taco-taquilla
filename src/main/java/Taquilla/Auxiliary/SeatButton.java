package Taquilla.Auxiliary;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.util.ArrayList;

public class SeatButton extends JButton {
    private final Color zoneColor;
    private Color pressedColor = new Color(255, 145, 164);
    private SeatState seatState;

    public SeatButton(SeatState seatState) {
        super(seatState.getSeat().toString());
        super.setContentAreaFilled(false);

        switch (seatState.getSeat().getZone().getId()){
            case 1: //Diamante
                zoneColor = new Color(4,176,69); break;
            case 2: //Oro
                zoneColor = new Color(253,247,9); break;
            case 3: //Plata
                zoneColor = new Color(86,166,218); break;
            case 4: //Cobre
                zoneColor = new Color(250,215,108); break;
            case 5: //Lata
                zoneColor = new Color(232,232,232); break;
            default:
                zoneColor = new Color(232,232,232); break;
        }
        if (seatState.getSeating() != null){
            //Different color for reserved seats
            if(seatState.getSeating().getState().toLowerCase().equals("reserved")) {
                this.pressedColor = new Color(204, 101, 254);
            }
            //Deactivation of reservations 2 hrs earlier
            if(!((seatState.getSeating().getState().toLowerCase().equals("reserved"))
                    && (seatState.getSeating().getShow().getTime().getHour()-2 < LocalTime.now().getHour()))
                    || seatState.getSeating().getState().toLowerCase().equals("taken")) {
                this.setEnabled(false);
                this.setSelected(true);
            }
        } else {
            pressedColor = new Color(255, 145, 164);
        }
        this.seatState = seatState;
        this.addActionListener(event->{
            this.setSelected(!this.isSelected());
        });
    }

    public SeatState getSeatState() {
        return seatState;
    }

    @Override
    public void setContentAreaFilled(boolean b) {
        System.out.println("uwu");
    }

    @Override
    protected void paintComponent(Graphics g){
        if (getModel().isSelected() || getModel().isPressed()){
            if (this.seatState.getSeating() != null && this.seatState.getSeating().getState().toLowerCase().equals("reserved"))
                this.pressedColor = new Color(204,101,254);
            else
                this.pressedColor = new Color(255, 145, 164);
            g.setColor(pressedColor);
        } else {
            g.setColor(zoneColor);
        }

        g.fillRect(0,0,getWidth(),getHeight());
        super.paintComponent(g);
    }


}
