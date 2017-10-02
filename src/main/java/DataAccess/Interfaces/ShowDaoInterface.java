package DataAccess.Interfaces;

import Elements.Show;

import java.time.LocalDate;
import java.util.List;

public interface ShowDaoInterface extends BaseDao{
    @Override List<Show> findAll();
    @Override Show findById(int id);
    List<Show> findByDate(LocalDate date);
}
