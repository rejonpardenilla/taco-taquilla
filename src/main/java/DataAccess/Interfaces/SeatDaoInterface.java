package DataAccess.Interfaces;
import Elements.Seat;

import java.util.List;

public interface SeatDaoInterface {
    List<Seat> findAll();
    Seat findById(int id);
}
