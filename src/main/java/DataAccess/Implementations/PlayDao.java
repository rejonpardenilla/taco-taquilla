package DataAccess.Implementations;

import DataAccess.ConnectionFactory;
import DataAccess.Interfaces.PlayDaoInterface;
import Elements.Person;
import Elements.Play;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlayDao extends BaseDao<Play> implements PlayDaoInterface {

    @Override
    public Play extractFromResultSet(ResultSet rs) throws SQLException {

        Play play = new Play();
        play.setId(rs.getInt("id"));
        play.setName(rs.getString("name"));
        play.setDescription(rs.getString("description"));

        PersonDao personDao = new PersonDao();
        play.setResponsible(
                personDao.findById(
                        rs.getInt("responsible")));

        play.setActors(getActors(play.getId()));
        play.setCancelled(rs.getBoolean("cancelled"));

        return play;

    }

    @Override
    public List<Person> getActors(int id){

        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;

        try{

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM actor_list WHERE show=" + id);

            ArrayList<Person> actors = new ArrayList<>();
            PersonDao personDao = new PersonDao();

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
    public List<Play> findByDate(LocalDate date) {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM play INNER JOIN show on play.id = show.play WHERE date='" + date + "'");

            ArrayList<Play> results = new ArrayList<Play>();
            while(resultSet.next()){
                Play result = extractFromResultSet(resultSet);
                results.add(result);
            }

            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int insertPlay(Play play, int responsibleId) throws SQLException{
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;

        String query = "INSERT INTO play (name, responsible, description, cancelled) VALUES (?, ?, ?, ?)";
        statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, String.valueOf(play.getName()));
        statement.setInt(2, responsibleId);
        statement.setString(3, String.valueOf(play.getDescription()));
        statement.setBoolean(4, play.isCancelled());

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

    public boolean isRegistered(String name) {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM play WHERE name='" + name + "'");

            if (resultSet.next())
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public int findByName(String name) {
        int playId = 0;
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM play WHERE name='" + name + "'");

            if (resultSet.next())
                playId = extractFromResultSet(resultSet).getId();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return playId;
    }

    public static void main(String[] args) {
        PlayDao playDao = new PlayDao();
        ArrayList<Play> plays = new ArrayList<>(playDao.findByDate(LocalDate.now()));
//        ShowDao showDao = new ShowDao();
//        showDao.findByDate(LocalDate.now());
        System.out.println("lol");
    }

}
