package DataAccess.Implementations;

import DataAccess.ConnectionFactory;
import DataAccess.Interfaces.ShowDaoInterface;
import Elements.Show;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ShowDao implements ShowDaoInterface {
    private Show extractShowFromResultSet(ResultSet rs) throws SQLException {
        PlayDao playDao = new PlayDao();
        Show show = new Show();
        show.setId(rs.getInt("id"));
        show.setDate(rs.getDate("date").toLocalDate());
        show.setTime(rs.getTime("time").toLocalTime());
        show.setPlay(
                playDao.findById(
                        rs.getInt("play")));
        show.setPrice(rs.getBigDecimal("price"));
        show.setCancelled(rs.getBoolean("cancelled"));
        return show;
    }

    @Override
    public List<Show> findAll() {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM show");

            ArrayList<Show> shows = new ArrayList<Show>();
            while(resultSet.next()){
                Show show = extractShowFromResultSet(resultSet);
                shows.add(show);
            }

            return shows;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Show findById(int id) {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM show WHERE id=" + id);

            if (resultSet.next())
                return extractShowFromResultSet(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
