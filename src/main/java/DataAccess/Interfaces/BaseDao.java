package DataAccess.Interfaces;

import Elements.Base.SerializedObject;

import java.sql.SQLException;
import java.util.List;

public interface BaseDao {
    List<? extends SerializedObject> findAll();
    SerializedObject findById(int id);
}
