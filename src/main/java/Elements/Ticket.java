package Elements;

import DataAccess.Implementations.TicketDao;
import Elements.Methods.DataAccessMethods;

import java.math.BigDecimal;
import java.sql.SQLException;

public class Ticket implements DataAccessMethods {
    int id;
    Seating seating;
    BigDecimal price; 
    boolean returned;
    Purchase purchase;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Seating getSeating() {
        return seating;
    }

    public void setSeating(Seating seating) {
        this.seating = seating;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    @Override
    public Ticket save() throws SQLException {
        TicketDao ticketDao = new TicketDao();
        this.id = ticketDao.insertTicket(this);
        return this;
    }
}
