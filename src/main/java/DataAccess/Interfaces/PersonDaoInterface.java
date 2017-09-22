package DataAccess.Interfaces;

import Elements.Person;

import java.util.List;

public interface PersonDaoInterface {
    List<Person> findAll();
    Person findById(int id);
}
