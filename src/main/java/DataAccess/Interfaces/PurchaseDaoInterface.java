package DataAccess.Interfaces;

import Elements.Purchase;

import java.sql.SQLException;
import java.util.List;

public interface PurchaseDaoInterface {
    List<Purchase> findAll();
    Purchase findById(int id);

    int insertPurchase(Purchase purchase) throws SQLException;
    int updatePurchase(Purchase purchase);
}
