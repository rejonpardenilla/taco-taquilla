package DataAccess.Implementations;

import DataAccess.ConnectionFactory;
import DataAccess.Interfaces.ZoneDaoInterface;
import Elements.Seating;
import Elements.Zone;

import java.sql.*;

public class ZoneDao extends BaseDao<Zone> implements ZoneDaoInterface {

    @Override
    public Zone extractFromResultSet(ResultSet rs) throws SQLException{

        Zone zone = new Zone();
        zone.setId(rs.getInt("id"));
        zone.setDiscountPercent(rs.getInt("discount_percent"));

        return zone;

    }

    public int insertZone(Zone zone) throws SQLException{
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;

        String query = "INSERT INTO zone (discount_percent) VALUES (?)";
        statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        statement.setInt(1, zone.getDiscountPercent());

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

}
