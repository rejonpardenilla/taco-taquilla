package DataAccess.Implementations;

import DataAccess.ConnectionFactory;
import DataAccess.Interfaces.BaseDaoInterface;
import Elements.Base.SerializedObject;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDao<T extends SerializedObject> implements BaseDaoInterface<T> {

    Class<T> typeOfT;
    String tableName;

    @SuppressWarnings("unchecked")
    public BaseDao() {
        this.typeOfT = (Class<T>)
                ((ParameterizedType)getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[0];
        this.tableName = typeOfT.getName().split("[.]")[1].toLowerCase();
    }

    @Override
    public List<T> findAll() {

        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);

            ArrayList<T> results = new ArrayList<T>();
            while(resultSet.next()){
                T result = extractFromResultSet(resultSet);
                results.add(result);
            }

            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public T findById(int id) {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName + " WHERE id=" + id);

            if (resultSet.next())
                return extractFromResultSet(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
