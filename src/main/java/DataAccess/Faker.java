package DataAccess;

import DataAccess.Implementations.SeatDao;
import Elements.Seat;
import Elements.Zone;

import java.sql.SQLException;

public class Faker {
    private static final int ROWS = 8;
    private static final int COLUMNS = 20;
    private static final String[] ROW_LETTERS = {"A", "B", "C", "D", "E", "F", "G", "H"};

    private static Zone makeZone(String name, int discount){
        Zone zone = new Zone();
        zone.setDiscountPercent(discount);
        zone.setName(name);
        return zone;
    }

    private static void populateSeats() {
        Zone zone = new Zone();
        zone.setDiscountPercent(0);
        try {
            Zone diamante = makeZone("Diamante", 100).save();
            Zone oro = makeZone("Oro", 90).save();
            Zone plata = makeZone("Plata", 75).save();
            Zone cobre = makeZone("Cobre", 60).save();
            Zone lata = makeZone("Lata", 50).save();

            SeatDao seatDao = new SeatDao();

            for (int row = 1; row <= ROWS; row++) {
                for (int column = 1; column <= COLUMNS; column++) {
                    switch(ROW_LETTERS[row - 1]){
                        case "H": case "G":
                            zone = (7 <= column && column >= 14) ? oro : plata;
                            break;
                        case "F": case "E": case "D":
                            zone = (7 <= column && column >= 14) ? diamante : lata;
                            break;
                        case "C": case "B": case "A":
                            zone = (7 <= column && column >= 14) ? cobre : lata;
                    }

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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Zone populated");
        }

    }

    public static void main(String[] args) {
        populateSeats();
    }
}
