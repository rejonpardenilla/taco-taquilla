package DataAccess.Interfaces;

import Elements.Purchase;

import java.sql.SQLException;

public interface PurchaseDaoInterface extends BaseDaoInterface<Purchase> {

    int insertPurchase(Purchase purchase) throws SQLException;
    int updatePurchase(Purchase purchase);
}
