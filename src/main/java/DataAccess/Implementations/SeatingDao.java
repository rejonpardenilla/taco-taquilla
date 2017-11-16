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
    public Seating extractFromResultSet(ResultSet rs) throws SQLException{
        SeatDao seatDao = new SeatDao();
        ShowDao showDao = new ShowDao();

        Seating seating = new Seating();
        seating.setId(rs.getInt("id"));
        seating.setState(rs.getString("state"));
        seating.setSeat(
                seatDao.findById(
                        rs.getInt("seat")));
        seating.setShow(
                showDao.findById(
                        rs.getInt("show")));
        return seating;
    }

    @Override
    public List<Seating> findByShow(Show show) {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM seating WHERE show=" + show.getId());

            ArrayList<Seating> seatings = new ArrayList<>();

            while (resultSet.next()) {
                Seating seating =  extractFromResultSet(resultSet);
                seatings.add(seating);
            }
            return seatings;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int insertSeating(Seating seating) throws SQLException{
        Connection connection = ConnectionFactory.getConnection();
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
                return generatedKeys.getInt("id");
            } else {
                throw new SQLException("No id obtained");
            }
        }
    }

    @Override
    public Seating findById(int id) {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM seating WHERE id=" + id);
            connection.close();
            if (resultSet.next())
                return extractFromResultSet(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
