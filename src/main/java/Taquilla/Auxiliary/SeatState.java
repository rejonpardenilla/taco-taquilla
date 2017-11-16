package Taquilla.Auxiliary;

import Elements.Seat;
import Elements.Seating;
import Elements.Show;

public class SeatState {
    Seat seat;
    Seating seating;

    public SeatState(Seat seat) {
        this.seat = seat;
    }

    public SeatState(Seat seat, Seating seating) {
        this.seat = seat;
        this.seating = seating;
    }

    public void modifySeatState(String status, Show show){
        if (this.seating == null){
            this.seating = new Seating(seat, show, status);
        } else {
            this.seating.setState(status);
        }
    }

    public Seating getSeating(){
        return this.seating;
    }

    public String getSeatingType(){
        return this.getSeating().getState().toLowerCase();
    }

    public Seat getSeat() {
        return seat;
    }
}
