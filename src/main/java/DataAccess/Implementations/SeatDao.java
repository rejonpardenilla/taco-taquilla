package DataAccess.Implementations;

import DataAccess.ConnectionFactory;
import DataAccess.Interfaces.SeatDaoInterface;
import Elements.Seat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SeatDao implements SeatDaoInterface {

    //general extracting method
    private Seat extractSeatFromResultSet(ResultSet rs) throws SQLException {
        ZoneDao zoneDao = new ZoneDao();
        Seat seat = new Seat();
        seat.setId(rs.getInt("id"));
        seat.setNumber(rs.getInt("number"));
        seat.setRow(rs.getString("row"));
        seat.setZone(zoneDao.findById(rs.getInt("zone")));
        return seat;
    }

    public List<Seat> findAll() {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM seat");

            ArrayList<Seat> seats = new ArrayList<Seat>();
            while(resultSet.next()){
                Seat seat = extractSeatFromResultSet(resultSet);
                seats.add(seat);
            }

            return seats;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Seat findById(int id) {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM seat WHERE id=" + id);

            if (resultSet.next())
                return extractSeatFromResultSet(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
