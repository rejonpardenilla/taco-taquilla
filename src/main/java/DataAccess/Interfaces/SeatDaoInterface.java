package DataAccess.Interfaces;
import Elements.Seat;

import java.util.List;

public interface SeatDaoInterface extends BaseDao{
    @Override List<Seat> findAll();
    @Override Seat findById(int id);
}
