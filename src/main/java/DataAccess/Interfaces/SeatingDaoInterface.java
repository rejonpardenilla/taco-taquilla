package DataAccess.Interfaces;

import Elements.Seating;

import java.sql.SQLException;
import java.util.List;

public interface SeatingDaoInterface {
    List<Seating> findAll();
    Seating findById(int id);

    int insertSeating(Seating seating) throws SQLException;
}
