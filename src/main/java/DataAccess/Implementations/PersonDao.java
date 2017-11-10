package DataAccess.Implementations;

import DataAccess.ConnectionFactory;
import DataAccess.Interfaces.PersonDaoInterface;
import Elements.Person;

import java.sql.*;
import java.util.ArrayList;

public class PersonDao extends BaseDao<Person> implements PersonDaoInterface {

    @Override
    public Person extractFromResultSet(ResultSet rs) throws SQLException {
        Person person = new Person();
        person.setId(rs.getInt("id"));
        person.setName(rs.getString("name"));
        person.setLastName(rs.getString("last_name"));
        person.setPhone(rs.getString("phone"));
        person.setPhoneAlt(rs.getString("phone_alt"));
        person.setEmail(rs.getString("email"));
        person.setType(rs.getString("type"));
        return person;
    }

    @Override
    public int insertPerson(Person person) throws SQLException{
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;

        String query = "INSERT INTO person (name, last_name, phone, phone_alt, type, email) VALUES (?, ?, ?, ?, ?, ?)";
        statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, person.getName());
        statement.setString(2, person.getLastName());
        statement.setString(3, person.getPhone());
        statement.setString(4, person.getPhoneAlt());
        statement.setString(5, person.getType());
        statement.setString(6, person.getEmail());

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
                    "email="     + person.getEmail() +
                    " WHERE id=" + person.getId();

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

    public boolean isRegistered(String firstName, String lastName) {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM person WHERE name='" + firstName + "' AND last_name='" + lastName + "'");

            if (resultSet.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Person findByName(String firstName, String lastName) {
        Person person = null;
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM person WHERE name='" + firstName + "' AND last_name='" + lastName + "'");

            if (resultSet.next())
                person = extractFromResultSet(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return person;
    }

    public static void main(String[] args) {
        PersonDao personDao = new PersonDao();
        Person person = new Person();
        person.setName("juanito");
        person.setLastName("jones");
        person.setType("todologo");

        ArrayList<Person> people = new ArrayList<>(personDao.findAll());

        person = personDao.findById(1);

        System.out.println(person.getClass().getName());
        try {
            int id = personDao.insertPerson(person);
            person.setId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
