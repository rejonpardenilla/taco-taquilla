package DataAccess.Implementations;

import DataAccess.ConnectionFactory;
import DataAccess.Interfaces.PurchaseDaoInterface;
import Elements.Purchase;

import java.sql.*;
import java.util.List;

public class PurchaseDao extends BaseDao<Purchase> implements PurchaseDaoInterface{

    @Override
    public Purchase extractFromResultSet(ResultSet rs) throws SQLException{
        PersonDao personDao = new PersonDao();
        Purchase purchase = new Purchase();
        purchase.setId(rs.getInt("id"));
        purchase.setClient(personDao.findById(rs.getInt("client")));
        purchase.setTotal(rs.getBigDecimal("total"));
        purchase.setDate(rs.getDate("date"));
        purchase.setTime(rs.getTime("time"));
        return purchase;
    }

    @Override
    public int insertPurchase(Purchase purchase) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;

        String query = "INSERT INTO purchase (client, total, date, time) VALUES (?,?,?,?)";
        statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, purchase.getClient().getId());
        statement.setBigDecimal(2, purchase.getTotal());
        statement.setDate(3, purchase.getDate());
        statement.setTime(4, purchase.getTime());


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
    public Purchase findById(int id) {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM purchase WHERE id=" + id);
            connection.close();
            if (resultSet.next())
                return extractFromResultSet(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updatePurchase(Purchase purchase) {
        return 0;
    }

    public static void main(String[] args) {
        PurchaseDao purchaseDao = new PurchaseDao();
//        PersonDao personDao = new PersonDao();
//
//        Purchase purchase = new Purchase();
//        Person client = personDao.findById(1);
//        purchase.setClient(client);
//        purchase.setTotal(BigDecimal.valueOf(29.96));
//        try {
//            int id = purchaseDao.insertPurchase(purchase);
//            purchase.setId(id);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        List<Purchase> hehe = purchaseDao.findAll();
        System.out.println("hehe");
    }
}
