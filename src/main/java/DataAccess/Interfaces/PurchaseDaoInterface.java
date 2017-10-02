package DataAccess.Interfaces;

import Elements.Purchase;

import java.sql.SQLException;
import java.util.List;

public interface PurchaseDaoInterface extends BaseDao {
    @Override List<Purchase> findAll();
    @Override Purchase findById(int id);

    int insertPurchase(Purchase purchase) throws SQLException;
    int updatePurchase(Purchase purchase);
}
