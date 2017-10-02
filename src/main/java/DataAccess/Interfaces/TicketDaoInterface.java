package DataAccess.Interfaces;

import Elements.Ticket;

import java.sql.SQLException;

public interface TicketDaoInterface extends BaseDaoInterface<Ticket> {

    int insertTicket(Ticket ticket) throws SQLException;
}
