package DataAccess.Interfaces;

import Elements.Seating;

import java.util.List;

public interface SeatingDaoInterface {
    List<Seating> findAll();
    Seating findById(int id);
}
