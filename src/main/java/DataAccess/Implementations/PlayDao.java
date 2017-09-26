package DataAccess.Implementations;

import DataAccess.ConnectionFactory;
import DataAccess.Interfaces.PlayDaoInterface;
import Elements.Person;
import Elements.Play;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlayDao implements PlayDaoInterface {

    private Play extractPlayFromResultSet(ResultSet rs) throws SQLException {
        PersonDao personDao = new PersonDao();
        Play play = new Play();
        play.setId(rs.getInt("id"));
        play.setName(rs.getString("name"));
        play.setDescription(rs.getString("description"));
        play.setResponsible(
                personDao.findById(
                        rs.getInt("responsible")));
        play.setActors(getActors(play.getId()));
        play.setCancelled(rs.getBoolean("cancelled"));
        return play;
    }

    public List<Person> getActors(int id){
        PersonDao personDao = new PersonDao();
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;
        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM actor_list WHERE show=" + id);

            ArrayList<Person> actors = new ArrayList<>();
            while(resultSet.next()){
                Person actor = personDao.findById(
                                    resultSet.getInt("actor"));
                actors.add(actor);
            }

            return actors;
        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Play> findAll() {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM play");

            ArrayList<Play> plays = new ArrayList<Play>();
            while(resultSet.next()){
                Play play = extractPlayFromResultSet(resultSet);
                plays.add(play);
            }

            return plays;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Play findById(int id) {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM play WHERE id=" + id);

            if (resultSet.next())
                return extractPlayFromResultSet(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}