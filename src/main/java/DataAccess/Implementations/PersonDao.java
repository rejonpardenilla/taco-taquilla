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
        person.setPhoneAlternative(rs.getString("phone_alternative"));
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
}
