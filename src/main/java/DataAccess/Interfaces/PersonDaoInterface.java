package DataAccess.Interfaces;

import Elements.Person;

import java.sql.SQLException;

public interface PersonDaoInterface extends BaseDaoInterface<Person> {

    public int insertPerson(Person person) throws SQLException;
    public boolean updatePerson(Person person);
    public boolean deletePersonById(int id);
}
