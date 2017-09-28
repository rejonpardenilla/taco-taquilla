package DataAccess.Interfaces;

import Elements.Ticket;

import java.sql.SQLException;
import java.util.List;

public interface TicketDaoInterface {
    List<Ticket> findAll();
    Ticket findById(int id);

    int insertTicket(Ticket ticket) throws SQLException;
}
