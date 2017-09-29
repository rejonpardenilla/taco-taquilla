package DataAccess.Interfaces;

import Elements.Seating;
import Elements.Show;

import java.sql.SQLException;
import java.util.List;

public interface SeatingDaoInterface {
    List<Seating> findAll();
    Seating findById(int id);
    List<Seating> findByShow(Show show);

    int insertSeating(Seating seating) throws SQLException;
}
