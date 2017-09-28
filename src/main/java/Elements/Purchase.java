package Elements;

import DataAccess.Implementations.PurchaseDao;
import Elements.Methods.DataAccessMethods;

import java.math.BigDecimal;
import java.sql.SQLException;

public class Purchase implements DataAccessMethods {

    int id;
    Person client;
    BigDecimal total;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public Purchase save() throws SQLException {
        PurchaseDao purchaseDao = new PurchaseDao();
        this.id = purchaseDao.insertPurchase(this);
        return this;
    }
}
