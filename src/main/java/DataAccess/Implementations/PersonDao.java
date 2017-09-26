package DataAccess.Implementations;

import DataAccess.ConnectionFactory;
import DataAccess.Interfaces.PersonDaoInterface;
import Elements.Person;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonDao implements PersonDaoInterface {
    private Person extractPersonFromResultSet(ResultSet rs) throws SQLException {
        Person person = new Person();
        person.setId(rs.getInt("id"));
        person.setName(rs.getString("name"));
        person.setLastName(rs.getString("last_name"));
        person.setPhone(rs.getString("phone"));
        person.setPhoneAlt(rs.getString("phone_alternative"));
        person.setEmail(rs.getString("email"));
        person.setType(rs.getString("type"));
        return person;
    }

    @Override
    public List<Person> findAll() {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM person");

            ArrayList<Person> people = new ArrayList<Person>();
            while(resultSet.next()){
                Person person = extractPersonFromResultSet(resultSet);
                people.add(person);
            }

            return people;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Person findById(int id){
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM person WHERE id=" + id);

            if (resultSet.next())
                return extractPersonFromResultSet(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertPerson(Person person) {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;
        try{
            statement = connection.createStatement();
            String query = "INSERT INTO person VALUES ("+
                            person.getName()        + ", " +
                            person.getLastName()    + ", " +
                            person.getPhone()       + ", " +
                            person.getPhoneAlt()    + ", " +
                            person.getType()        + ", " +
                            person.getEmail()       + ")";
            int rowsAffected = statement.executeUpdate(query);
            if(rowsAffected == 1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public boolean updatePerson(Person person) {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;
        try{
            statement = connection.createStatement();
            String query = "UPDATE person SET "+
                    "name="      + person.getName()     + ", " +
                    "last_name=" + person.getLastName() + ", " +
                    "phone="     + person.getPhone()    + ", " +
                    "phone_alt=" + person.getPhoneAlt() + ", " +
                    "type="      + person.getType()     + ", " +
                    "email="     + person.getEmail();
            int rowsAffected = statement.executeUpdate(query);
            if(rowsAffected == 1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deletePersonById(int id) {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;
        try{
            statement = connection.createStatement();
            int rowsAffected = statement.executeUpdate("DELETE FROM person WHERE id=" + id);
            if(rowsAffected == 1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
