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

    public static void main(String[] args) {
        PlayDao playDao = new PlayDao();
        ArrayList<Play> plays = new ArrayList<>(playDao.findByDate(LocalDate.now()));
//        ShowDao showDao = new ShowDao();
//        showDao.findByDate(LocalDate.now());
        System.out.println("lol");
    }

}
