package DataAccess.Implementations;

import DataAccess.ConnectionFactory;
import DataAccess.Interfaces.ShowDaoInterface;
import Elements.Play;
import Elements.Show;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            ResultSet resultSet = statement.executeQuery("SELECT * FROM show WHERE date = '" + date+ "' ORDER BY play");

            ArrayList<Show> shows = new ArrayList<>();

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

            ArrayList<Show> shows = new ArrayList<>();

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
}
