package DataAccess.Implementations;

import DataAccess.ConnectionFactory;
import DataAccess.Interfaces.PurchaseDaoInterface;
import Elements.Person;
import Elements.Purchase;

import java.math.BigDecimal;
import java.sql.*;

public class PurchaseDao extends BaseDao<Purchase> implements PurchaseDaoInterface{

    @Override
    public Purchase extractFromResultSet(ResultSet rs) throws SQLException{
        PersonDao personDao = new PersonDao();
        Purchase purchase = new Purchase();
        purchase.setId(rs.getInt("id"));
        purchase.setClient(personDao.findById(rs.getInt("client")));
        purchase.setTotal(rs.getBigDecimal("total"));
        return purchase;
    }

    @Override
    public int insertPurchase(Purchase purchase) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;

        String query = "INSERT INTO purchase (client, total) VALUES (?,?)";
        statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, purchase.getClient().getId());
        statement.setBigDecimal(2, purchase.getTotal());


        int rowsAffected = statement.executeUpdate();
        if(rowsAffected == 0){
            throw new SQLException("No rows affected");
        }

        try(ResultSet generatedKeys = statement.getGeneratedKeys()){
            if (generatedKeys.next()){
                return generatedKeys.getInt("id");
            } else {
                throw new SQLException("No id obtained");
            }
        }
    }

    @Override
    public int updatePurchase(Purchase purchase) {
        return 0;
    }

    public static void main(String[] args) {
        PurchaseDao purchaseDao = new PurchaseDao();
        PersonDao personDao = new PersonDao();

        Purchase purchase = new Purchase();
        Person client = personDao.findById(1);
        purchase.setClient(client);
        purchase.setTotal(BigDecimal.valueOf(29.96));
        try {
            int id = purchaseDao.insertPurchase(purchase);
            purchase.setId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
