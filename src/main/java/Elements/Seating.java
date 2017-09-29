package Elements;

import DataAccess.Implementations.SeatingDao;
import Elements.Methods.DataAccessMethods;
import Elements.Methods.SerializedObject;

import java.sql.SQLException;

public class Seating extends SerializedObject implements DataAccessMethods {
    String state; //FREE, TAKEN, RESERVED
    Seat seat;
    Show show;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    @Override
    public Seating save() throws SQLException{
        SeatingDao seatingDao = new SeatingDao();
        this.id = seatingDao.insertSeating(this);
        return this;
    }
}
