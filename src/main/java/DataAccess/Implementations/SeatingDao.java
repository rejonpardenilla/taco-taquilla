package DataAccess.Implementations;

import DataAccess.ConnectionFactory;
import DataAccess.Interfaces.SeatingDaoInterface;
import Elements.Seating;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SeatingDao implements SeatingDaoInterface{

    private Seating extractSeatingFromResultSet(ResultSet rs) throws SQLException{

        Seating seating = new Seating();
        seating.setId(rs.getInt("id"));
        seating.setState(rs.getString("state"));

        SeatDao seatDao = new SeatDao();
        seating.setSeat(
                seatDao.findById(
                        rs.getInt("seat")));

        ShowDao showDao = new ShowDao();
        seating.setShow(
                showDao.findById(
                        rs.getInt("show")));

        return seating;

    }

    @Override
    public List<Seating> findAll() {

        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;

        try {

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM seating");

            ArrayList<Seating> seatings = new ArrayList<>();

            while (resultSet.next()) {
                Seating seating =  extractSeatingFromResultSet(resultSet);
                seatings.add(seating);
            }

            return seatings;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public Seating findById(int id) {

        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;

        try {

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM seating WHERE id=" + id);

            if (resultSet.next())
                return extractSeatingFromResultSet(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

}
