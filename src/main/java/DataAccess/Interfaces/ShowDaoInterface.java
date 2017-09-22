package DataAccess.Interfaces;

import Elements.Show;

import java.util.List;

public interface ShowDaoInterface {
    List<Show> findAll();
    Show findById(int id);
}
