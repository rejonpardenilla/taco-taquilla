package DataAccess.Implementations;

import DataAccess.ConnectionFactory;
import DataAccess.Interfaces.SeatDaoInterface;
import Elements.Seat;

import java.sql.*;

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

    public int insertSeat(Seat seat) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;

        String query = "INSERT INTO seat (row, number, zone) VALUES (?, ?, ?)";
        statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, seat.getRow());
        statement.setInt(2, seat.getNumber());
        statement.setInt(3, seat.getZone().getId());

        int rowsAffected = statement.executeUpdate();
        if (rowsAffected == 0) {
            throw new SQLException("No rows affected");
        }

        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getInt("id");
            } else {
                throw new SQLException("No id obtained");
            }
        }
    }

}
