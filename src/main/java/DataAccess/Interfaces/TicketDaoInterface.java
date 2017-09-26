package DataAccess.Interfaces;

import Elements.Ticket;

import java.util.List;

public interface TicketDaoInterface {
    List<Ticket> findAll();
    Ticket findById(int id);
}
