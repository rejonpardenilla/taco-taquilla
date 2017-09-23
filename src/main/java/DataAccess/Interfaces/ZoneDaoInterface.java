package DataAccess.Interfaces;

import Elements.Zone;

import java.util.List;

public interface ZoneDaoInterface {
    List<Zone> findAll();
    Zone findById(int id);
}
