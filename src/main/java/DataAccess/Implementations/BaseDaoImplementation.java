package DataAccess.Implementations;

import DataAccess.Interfaces.BaseDao;
import Elements.Base.SerializedObject;

import java.util.List;

public abstract class BaseDaoImplementation implements BaseDao{
    @Override
    public List<? extends SerializedObject> findAll() {
        return null;
    }

    @Override
    public SerializedObject findById(int id) {
        return null;
    }
}
