package DataAccess.Implementations;

import DataAccess.ConnectionFactory;
import DataAccess.Interfaces.ZoneDaoInterface;
import Elements.Zone;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ZoneDao implements ZoneDaoInterface {

    private Zone extractZoneFromResultSet(ResultSet rs) throws SQLException{

        Zone zone = new Zone();
        zone.setId(rs.getInt("id"));
        zone.setDiscountPercent(rs.getInt("discount_percent"));

        return zone;

    }

    @Override
    public List<Zone> findAll() {

        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;

        try {

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM 'zone'");

            ArrayList<Zone> zones = new ArrayList<>();

            while(resultSet.next()){
                Zone zone = extractZoneFromResultSet(resultSet);
                zones.add(zone);
            }

            return zones;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public Zone findById(int id) {

        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;

        try {

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM zone WHERE id=" + id);

            if (resultSet.next())
                return extractZoneFromResultSet(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

}
