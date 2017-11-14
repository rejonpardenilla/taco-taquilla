package DataAccess;

import DataAccess.Implementations.SeatDao;
import Elements.Seat;
import Elements.Zone;

import java.sql.SQLException;

public class Faker {
    private static final int ROWS = 8;
    private static final int COLUMNS = 20;
    private static final String[] ROW_LETTERS = {"A", "B", "C", "D", "E", "F", "G", "H"};

    private static void populateSeats() {
        Zone zone = new Zone();
        zone.setDiscountPercent(0);
        try {
            zone.save();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Zone populated");
        }
        SeatDao seatDao = new SeatDao();
        for (int row = 1; row <= ROWS; row++) {
            for (int column = 1; column <= COLUMNS; column++) {
                Seat seat = new Seat();
                seat.setRow(ROW_LETTERS[row - 1]);
                seat.setNumber(column);
                seat.setZone(zone);
                try {
                    seatDao.insertSeat(seat);
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("Seats populated");
                }
            }
        }
    }

    public static void main(String[] args) {
        populateSeats();
    }
}
