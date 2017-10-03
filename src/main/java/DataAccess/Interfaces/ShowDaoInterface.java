package DataAccess.Interfaces;

import Elements.Play;
import Elements.Show;

import java.time.LocalDate;
import java.util.List;

public interface ShowDaoInterface extends BaseDaoInterface<Show> {

    List<Show> findByDate(LocalDate date);
    List<Show> findByPlay(Play play);
}
