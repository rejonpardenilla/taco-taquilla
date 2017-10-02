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

}
