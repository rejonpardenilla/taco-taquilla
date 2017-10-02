package DataAccess.Implementations;

import DataAccess.ConnectionFactory;
import DataAccess.Interfaces.TicketDaoInterface;
import Elements.Ticket;

import java.math.BigDecimal;
import java.sql.*;

public class TicketDao extends BaseDao<Ticket> implements TicketDaoInterface {

    @Override
    public Ticket extractFromResultSet(ResultSet rs) throws SQLException{
        SeatingDao seatingDao = new SeatingDao();
        Ticket ticket = new Ticket();
        ticket.setId(rs.getInt("id"));
        ticket.setPrice(rs.getBigDecimal("price"));
        ticket.setReturned(rs.getBoolean("returned"));
        ticket.setSeating(seatingDao.findById(rs.getInt("seating")));
        return ticket;
    }

    @Override
    public int insertTicket(Ticket ticket) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;

        String query = "INSERT INTO ticket (seating, returned, price, purchase) VALUES (?, ?, ?, ?)";
        statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        statement.setInt(1, ticket.getSeating().getId());
        statement.setBoolean(2, ticket.isReturned());
        statement.setBigDecimal(3, ticket.getPrice());
        statement.setInt(4, ticket.getPurchase().getId());

        int rowsAffected = statement.executeUpdate();
        if(rowsAffected == 0){
            throw new SQLException("No rows affected");
        }

        try(ResultSet generatedKeys = statement.getGeneratedKeys()){
            if (generatedKeys.next()){
                return generatedKeys.getInt("id");
            } else {
                throw new SQLException("No id obtained");
            }
        }

    }

    public static void main(String[] args) {
        TicketDao ticketDao = new TicketDao();
        SeatingDao seatingDao = new SeatingDao();
        PurchaseDao purchaseDao = new PurchaseDao();
        ShowDao showDao = new ShowDao();

        Ticket ticket = new Ticket();

        ticket.setPurchase(purchaseDao.findById(1));
        ticket.setPrice(BigDecimal.valueOf(69.96));
        ticket.setReturned(false);
        ticket.setSeating(seatingDao.findById(1));

        try {
            int id = ticketDao.insertTicket(ticket);
            ticket.setId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
