package DataAccess.Interfaces;

import Elements.Ticket;

import java.sql.SQLException;
import java.util.List;

public interface TicketDaoInterface extends BaseDao{
    @Override List<Ticket> findAll();
    @Override Ticket findById(int id);

    int insertTicket(Ticket ticket) throws SQLException;
}
