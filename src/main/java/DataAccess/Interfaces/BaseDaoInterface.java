package DataAccess.Interfaces;

import Elements.Base.SerializedObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface BaseDaoInterface<T extends SerializedObject> {
    T extractFromResultSet(ResultSet resultSet) throws SQLException;
    List<T> findAll();
    T findById(int id);
}
