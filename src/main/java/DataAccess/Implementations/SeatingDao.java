package DataAccess.Implementations;

import DataAccess.ConnectionFactory;
import DataAccess.Interfaces.SeatingDaoInterface;
import Elements.Seating;
import Elements.Show;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeatingDao extends BaseDao<Seating> implements SeatingDaoInterface{

    @Override
    public Seating extractFromResultSet(ResultSet rs) throws SQLException {
        return extractFromResultSet(rs, ConnectionFactory.getConnection());
    }

    public Seating extractFromResultSet(ResultSet rs, Connection connection) throws SQLException{
        SeatDao seatDao = new SeatDao();
        ShowDao showDao = new ShowDao();

        Seating seating = new Seating();
        seating.setId(rs.getInt("id"));
        seating.setState(rs.getString("state"));
        seating.setSeat(
                seatDao.findById(
                        rs.getInt("seat"), connection));
        seating.setShow(
                showDao.findById(
                        rs.getInt("show"), connection));
        return seating;
    }

    @Override
    public List<Seating> findByShow(Show show) {
        return findByShow(show, ConnectionFactory.getConnection());
    }

    public List<Seating> findByShow(Show show, Connection connection){
        this.connection = connection;
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM seating WHERE show=" + show.getId());

            ArrayList<Seating> seatings = new ArrayList<>();

            while (resultSet.next()) {
                Seating seating =  extractFromResultSet(resultSet, connection);
                seatings.add(seating);
            }
            connection.close();
            return seatings;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int insertSeating(Seating seating) throws SQLException {
        return insertSeating(seating, connection);
    }
    public int insertSeating(Seating seating, Connection connection) throws SQLException{
        this.connection = connection;
        PreparedStatement statement = null;

        String query = "INSERT INTO seating (seat, state, show) VALUES (?, ?, ?)";
        statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        statement.setInt(1, seating.getSeat().getId());
        statement.setString(2, seating.getState());
        statement.setInt(3, seating.getShow().getId());

        int rowsAffected = statement.executeUpdate();
        if(rowsAffected == 0){
            throw new SQLException("No rows affected");
        }

        try(ResultSet generatedKeys = statement.getGeneratedKeys()){
            if (generatedKeys.next()){
                connection.close();
                return generatedKeys.getInt("id");
            } else {
                connection.close();
                throw new SQLException("No id obtained");
            }
        }
    }

    public static void main(String[] args) {
        SeatingDao seatingDao = new SeatingDao();
        ShowDao showDao = new ShowDao();

        for (int i = 1; i <= 15; i++) {
            SeatDao seatDao = new SeatDao();
            Seating seating = new Seating();
            seating.setSeat(seatDao.findById(i));
            if (i % 2 == 0) {
                seating.setState("TAKEN");
            } else {
                seating.setState("AVAILABLE");
            }
            seating.setShow(showDao.findById(1));

            try {
                seating.save();
//            int id = seatingDao.insertSeating(seating);
//            seating.setId(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
