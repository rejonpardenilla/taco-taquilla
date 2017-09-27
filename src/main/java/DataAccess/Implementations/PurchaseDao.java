package DataAccess.Implementations;

import DataAccess.ConnectionFactory;
import DataAccess.Interfaces.PurchaseDaoInterface;
import Elements.Purchase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PurchaseDao implements PurchaseDaoInterface{

    private Purchase extractPurchaseFromResultSet(ResultSet rs) throws SQLException{

        Purchase purchase = new Purchase();
        purchase.setId(rs.getInt("id"));

        PersonDao personDao = new PersonDao();
        purchase.setClient(personDao.findById(rs.getInt("client")));

        purchase.setTotal(rs.getBigDecimal("total"));

        return purchase;

    }

    @Override
    public List<Purchase> findAll() {

        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;

        try{

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM purchase");

            ArrayList<Purchase> purchases = new ArrayList<>();

            while(resultSet.next()){
                Purchase purchase = extractPurchaseFromResultSet(resultSet);
                purchases.add(purchase);
            }

            return purchases;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public Purchase findById(int id) {

        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;

        try{

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM purchase WHERE id="+id);

            if (resultSet.next()){
                return extractPurchaseFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

}
