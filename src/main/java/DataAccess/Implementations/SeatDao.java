package DataAccess.Implementations;

import DataAccess.Interfaces.SeatDaoInterface;
import Elements.Seat;
import Elements.Zone;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SeatDao extends BaseDao<Seat> implements SeatDaoInterface {

    @Override
    public Seat extractFromResultSet(ResultSet rs) throws SQLException {

        Seat seat = new Seat();
        seat.setId(rs.getInt("id"));
        seat.setNumber(rs.getInt("number"));
        seat.setRow(rs.getString("row"));

        ZoneDao zoneDao = new ZoneDao();
        seat.setZone(zoneDao.findById(rs.getInt("zone")));

        return seat;

    }

    public static void main(String[] args) {
        SeatDao seatDao = new SeatDao();
        Seat seat = new Seat();
        ZoneDao zoneDao = new ZoneDao();
        seat.setZone(zoneDao.findById(1));
        seat.setRow("J");
        seat.setNumber(3);
        try {
            seatDao.insert(seat);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Zone zz = new Zone();
        zz.setDiscountPercent(12);
        try {
            zoneDao.insert(zz);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
