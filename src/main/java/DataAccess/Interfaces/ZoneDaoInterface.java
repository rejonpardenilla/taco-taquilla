package DataAccess.Interfaces;

import Elements.Zone;

import java.util.List;

public interface ZoneDaoInterface extends BaseDao {
    @Override List<Zone> findAll();
    @Override Zone findById(int id);
}
