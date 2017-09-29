package Elements;

import DataAccess.Implementations.PurchaseDao;
import Elements.Methods.DataAccessMethods;
import Elements.Methods.SerializedObject;

import java.math.BigDecimal;
import java.sql.SQLException;

public class Purchase extends SerializedObject implements DataAccessMethods {

    Person client;
    BigDecimal total;


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
