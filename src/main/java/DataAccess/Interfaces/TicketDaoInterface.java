package DataAccess.Interfaces;

import Elements.Play;
import Elements.Show;
import Elements.Ticket;

import java.sql.SQLException;
import java.util.List;

public interface TicketDaoInterface extends BaseDaoInterface<Ticket> {

    int insertTicket(Ticket ticket) throws SQLException;

    List<Ticket> findByShow(Show show);

    List<Ticket> findByPlay(Play play);
}
