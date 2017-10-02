package DataAccess.Implementations;

import DataAccess.Interfaces.ZoneDaoInterface;
import Elements.Zone;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ZoneDao extends BaseDao<Zone> implements ZoneDaoInterface {

    @Override
    public Zone extractFromResultSet(ResultSet rs) throws SQLException{

        Zone zone = new Zone();
        zone.setId(rs.getInt("id"));
        zone.setDiscountPercent(rs.getInt("discount_percent"));

        return zone;

    }

}
