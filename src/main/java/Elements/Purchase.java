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

    public Time getTime() { return time; }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public Purchase save() throws SQLException {
        PurchaseDao purchaseDao = new PurchaseDao();
        this.date = Date.valueOf(LocalDate.now());
        this.time = Time.valueOf(LocalTime.now());
        this.id = purchaseDao.insertPurchase(this);
        return this;
    }
}
