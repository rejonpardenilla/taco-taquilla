package DataAccess.Interfaces;

import Elements.Show;

import java.time.LocalDate;
import java.util.List;

public interface ShowDaoInterface {
    List<Show> findAll();
    Show findById(int id);
    List<Show> findByDate(LocalDate date);
}
