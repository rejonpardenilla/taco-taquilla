package DataAccess.Implementations;

import DataAccess.ConnectionFactory;
import DataAccess.Interfaces.ShowDaoInterface;
import Elements.Play;
import Elements.Show;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ShowDao extends BaseDao<Show> implements ShowDaoInterface {

    @Override
    public Show extractFromResultSet(ResultSet rs) throws SQLException {

        Show show = new Show();
        show.setId(rs.getInt("id"));
        show.setDate(rs.getDate("date").toLocalDate());
        show.setTime(rs.getTime("time").toLocalTime());

        PlayDao playDao = new PlayDao();
        show.setPlay(
                playDao.findById(
                        rs.getInt("play")));

        show.setPrice(rs.getBigDecimal("price"));
        show.setCancelled(rs.getBoolean("cancelled"));

        return show;

    }

    @Override
    public List<Show> findByDate(LocalDate date){

        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;

        try{

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM show WHERE date = '" + date + "' ORDER BY play");

            ArrayList<Show> shows = new ArrayList<Show>();

            while(resultSet.next()){
                Show show = extractFromResultSet(resultSet);
                shows.add(show);
            }

            return shows;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public List<Show> findByPlay(Play play) {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;

        try{

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM show WHERE play=" + play.getId());

            ArrayList<Show> shows = new ArrayList<Show>();

            while(resultSet.next()){
                Show show = extractFromResultSet(resultSet);
                shows.add(show);
            }

            return shows;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int insertShow(Show show, int playId) throws SQLException{
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;

        String query = "INSERT INTO show (date, time, play, price, cancelled) VALUES (?, ?, ?, ?, ?)";
        statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        statement.setDate(1, Date.valueOf(show.getDate()));
        statement.setTime(2, Time.valueOf(show.getTime()));
        statement.setInt(3, playId);
        statement.setBigDecimal(4, show.getPrice());
        statement.setBoolean(5, show.isCancelled());

        int rowsAffected = statement.executeUpdate();
        if(rowsAffected == 0){
            throw new SQLException("No rows affected");
        }

        try(ResultSet generatedKeys = statement.getGeneratedKeys()){
            if (generatedKeys.next()){
                int id = generatedKeys.getInt("id");
                return id;
            } else {
                throw new SQLException("No id obtained");
            }
        }
    }

    public boolean updateShow(Show show) {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;

        try{
            statement = connection.createStatement();
            String query = "UPDATE show SET "+
                    "date='"        + show.getDate()            + "', " +
                    "time='"        + show.getTime()            + "', " +
                    "play="         + show.getPlay().getId()    + ", " +
                    "price="        + show.getPrice()           + ", " +
                    "cancelled="    + show.isCancelled() +
                    " WHERE id=" + show.getId();

            int rowsAffected = statement.executeUpdate(query);

            if(rowsAffected == 1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteShowById(int id) {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;

        try{
            statement = connection.createStatement();
            int rowsAffected = statement.executeUpdate("DELETE FROM show WHERE id=" + id);

            if(rowsAffected == 1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
