package DataAccess.Interfaces;

import Elements.Person;

import java.util.List;

public interface PersonDaoInterface {
    List<Person> findAll();
    Person findById(int id);

    public boolean insertPerson(Person person);
    public boolean updatePerson(Person person);
    public boolean deletePersonById(int id);
}
