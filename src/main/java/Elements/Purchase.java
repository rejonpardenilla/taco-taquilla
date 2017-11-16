package Elements;

import DataAccess.Implementations.PurchaseDao;
import Elements.Base.DataAccessMethods;
import Elements.Base.SerializedObject;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class Purchase extends SerializedObject implements DataAccessMethods {
    private Person client;
    private BigDecimal total;
    private Date date;
    private Time time;

    public Person getClient() {
        return client;
    }

    public void setClient(Person client) {
        this.client = client;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public Time getTime() { return time; }

    public void setTime(Time time) { this.time = time; }

    @Override
    public Purchase save() throws SQLException {
        PurchaseDao purchaseDao = new PurchaseDao();
        setDate(Date.valueOf(LocalDate.now()));
        System.out.println("date: " + this.date);
        setTime(Time.valueOf(LocalTime.now()));
        System.out.println("time: " + this.time);
        this.id = purchaseDao.insertPurchase(this);
        return this;
    }

    public static void main(String[] args) {
        Purchase purchase = new Purchase();
        Person client = new Person();
        client.setName("Daniel");
        client.setLastName("Rejon");
        client.setPhone("9991234567");
        client.setEmail("rejonpardenilla@codn.mx");
        client.setType("client");
        purchase.setTotal(new BigDecimal(420));
        purchase.setDate(Date.valueOf(LocalDate.now()));
        purchase.setTime(Time.valueOf(LocalTime.now()));
        try {
            purchase.setClient(client.save());
            purchase.save();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
