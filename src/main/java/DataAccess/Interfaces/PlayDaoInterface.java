package DataAccess.Interfaces;

import Elements.Person;
import Elements.Play;

import java.time.LocalDate;
import java.util.List;

public interface PlayDaoInterface extends BaseDaoInterface<Play> {

    List<Person> getActors(int id);

    List<Play> findByDate(LocalDate date);
}
