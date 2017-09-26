package DataAccess.Interfaces;

import Elements.Purchase;

import java.util.List;

public interface PurchaseDaoInterface {
    List<Purchase> findAll();
    Purchase findById(int id);
}
