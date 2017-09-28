package DataAccess.Interfaces;

import Elements.Person;

import java.sql.SQLException;
import java.util.List;

public interface PersonDaoInterface {
    List<Person> findAll();
    Person findById(int id);

    public int insertPerson(Person person) throws SQLException;
    public boolean updatePerson(Person person);
    public boolean deletePersonById(int id);
}
