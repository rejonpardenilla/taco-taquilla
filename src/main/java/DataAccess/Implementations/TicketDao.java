package DataAccess.Implementations;

import DataAccess.ConnectionFactory;
import DataAccess.Interfaces.TicketDaoInterface;
import Elements.Ticket;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TicketDao implements TicketDaoInterface {

    private Ticket extractTicketFromResultSet(ResultSet rs) throws SQLException{
        SeatingDao seatingDao = new SeatingDao();
        Ticket ticket = new Ticket();
        ticket.setId(rs.getInt("id"));
        ticket.setPrice(rs.getBigDecimal("price"));
        ticket.setReturned(rs.getBoolean("returned"));
        ticket.setSeating(seatingDao.findById(rs.getInt("seating")));
        return ticket;
    }
    @Override
    public List<Ticket> findAll() {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ticket");

            ArrayList<Ticket> tickets = new ArrayList<>();
            while (resultSet.next()) {
                Ticket ticket = extractTicketFromResultSet(resultSet);
                tickets.add(ticket);
            }
            return tickets;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Ticket findById(int id) {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ticket WHERE id=" + id);

            if (resultSet.next())
                return extractTicketFromResultSet(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
