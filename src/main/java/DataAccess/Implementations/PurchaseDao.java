package DataAccess.Implementations;

import DataAccess.ConnectionFactory;
import DataAccess.Interfaces.PurchaseDaoInterface;
import Elements.Person;
import Elements.Purchase;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PurchaseDao implements PurchaseDaoInterface{
    private Purchase extractPurchaseFromResultSet(ResultSet rs) throws SQLException{
        PersonDao personDao = new PersonDao();
        Purchase purchase = new Purchase();
        purchase.setId(rs.getInt("id"));
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

    @Override
    public int updatePurchase(Purchase purchase) {
        return 0;
    }
}
