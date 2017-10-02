package DataAccess.Implementations;

import DataAccess.Interfaces.SeatDaoInterface;
import Elements.Seat;

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


}
