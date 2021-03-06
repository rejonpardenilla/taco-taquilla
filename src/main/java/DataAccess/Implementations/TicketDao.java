package DataAccess.Implementations;

import DataAccess.ConnectionFactory;
import DataAccess.Interfaces.TicketDaoInterface;
import Elements.*;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDao extends BaseDao<Ticket> implements TicketDaoInterface {

    @Override
    public Ticket extractFromResultSet(ResultSet rs) throws SQLException{
        SeatingDao seatingDao = new SeatingDao();
        PurchaseDao purchaseDao = new PurchaseDao();
        Ticket ticket = new Ticket();
        ticket.setId(rs.getInt("id"));
        ticket.setPrice(rs.getBigDecimal("price"));
        ticket.setReturned(rs.getBoolean("returned"));
        ticket.setSeating(seatingDao.findById(rs.getInt("seating")));
        ticket.setPurchase(purchaseDao.findById(rs.getInt("purchase")));
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

    @Override
    public List<Ticket> findByShow(Show show) {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM play INNER JOIN show on play.id = show.play WHERE date='" + date + "'");
            ResultSet resultSet = statement.executeQuery(
                    "SELECT ticket.id as id, ticket.seating, ticket.returned, ticket.price, ticket.purchase " +
                            "FROM " +
                            "(ticket " +
                            "INNER JOIN " +
                            "(SELECT id as sid, show FROM seating) s " +
                            "on ticket.seating = s.sid) WHERE s.show =" + show.getId());

            ArrayList<Ticket> results = new ArrayList<Ticket>();
            while(resultSet.next()){
                Ticket result = new Ticket();
                result.setId(resultSet.getInt("id"));
                result.setPrice(resultSet.getBigDecimal("price"));
                result.setReturned(resultSet.getBoolean("returned"));
                Seating seating = new Seating();
                seating.setId(resultSet.getInt("seating"));
                result.setSeating(seating);
                Purchase purchase = new Purchase();
                purchase.setId(resultSet.getInt("purchase"));
                result.setPurchase(purchase);
                results.add(result);
            }

            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Ticket> findByPlay(Play play){
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT tinfo.id as id, tinfo.seating as seating, tinfo.returned, tinfo.price, tinfo.purchase " +
                        "FROM (" +
                            "(SELECT id, seating as tseating, returned, price, purchase FROM ticket) t " +
                            "INNER JOIN " +
                                "((SELECT id as seating, show FROM seating) se " +
                                "INNER JOIN " +
                                "(SELECT id as showid, play FROM show) s " +
                                "ON se.show = s.showid)" +
                            "ON tseating = seating) tinfo " +
                        "WHERE tinfo.play =" + play.getId());

            ArrayList<Ticket> results = new ArrayList<Ticket>();
            while(resultSet.next()){
                Ticket result = new Ticket();
                result.setId(resultSet.getInt("id"));
                result.setPrice(resultSet.getBigDecimal("price"));
                result.setReturned(resultSet.getBoolean("returned"));
                Seating seating = new Seating();
                seating.setId(resultSet.getInt("seating"));
                result.setSeating(seating);
                Purchase purchase = new Purchase();
                purchase.setId(resultSet.getInt("purchase"));
                result.setPurchase(purchase);
                results.add(result);
            }

            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        TicketDao ticketDao = new TicketDao();
        SeatingDao seatingDao = new SeatingDao();
        PurchaseDao purchaseDao = new PurchaseDao();
        ShowDao showDao = new ShowDao();
        PlayDao playDao = new PlayDao();

        Ticket ticket = new Ticket();

        ticket.setPurchase(purchaseDao.findById(1));
        ticket.setPrice(BigDecimal.valueOf(69.96));
        ticket.setReturned(false);
        ticket.setSeating(seatingDao.findById(1));

        try {
            int id = ticketDao.insertTicket(ticket);
            ticket.setId(id);
            List<Ticket> tickets = ticketDao.findByPlay(playDao.findById(2));
            System.out.println(tickets);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
