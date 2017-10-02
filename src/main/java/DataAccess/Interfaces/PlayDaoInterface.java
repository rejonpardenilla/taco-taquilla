package DataAccess.Interfaces;

import Elements.Play;

import java.util.List;

public interface PlayDaoInterface extends BaseDao{
    @Override List<Play> findAll();
    @Override Play findById(int id);
}
