package DataAccess.Implementations;

import DataAccess.ConnectionFactory;
import DataAccess.Interfaces.BaseDaoInterface;
import Elements.Base.SerializedObject;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDao<T extends SerializedObject> implements BaseDaoInterface<T> {

    private Class<T> typeOfT;
    private String tableName;
    public Connection connection;

    public Connection getConnection(){ return this.connection;}

    @SuppressWarnings("unchecked")
    public BaseDao() {
        this.typeOfT = (Class<T>)
                ((ParameterizedType)getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[0];
        this.tableName = typeOfT.getName().split("[.]")[1].toLowerCase();
    }

    @Override
    public List<T> findAll(){
        Connection newConnection = ConnectionFactory.getConnection();
        List<T> result = findAll(newConnection);
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<T> findAll(Connection connection) {
        this.connection = connection;
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);

            ArrayList<T> results = new ArrayList<T>();
            while(resultSet.next()){
                T result = extractFromResultSet(resultSet);
                results.add(result);
            }
            connection.close();
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public T findById(int id) {
        Connection newConnection = ConnectionFactory.getConnection();
        T result = findById(id, newConnection);
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public T findById(int id, Connection connection){
        this.connection = connection;
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

    @Override
    public int insert(T object) throws SQLException{
        Connection newConnection = ConnectionFactory.getConnection();
        int result = insert(object, newConnection);
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int insert(T object, Connection connection) throws SQLException{
        this.connection = connection;
        PreparedStatement statement = null;
        Class<?> objClass = object.getClass();
        String query = "INSERT INTO " + tableName + "(";
        Field[] fields = objClass.getDeclaredFields();

        for (Field field : fields){
            String fieldName = "";
            for(int i = 0; i < field.getName().length(); i++){
                fieldName += Character.isUpperCase(field.getName().charAt(i))
                                ? "_"+field.getName().toLowerCase().charAt(i)
                                : field.getName().charAt(i);
            }
            query += fieldName + ",";
        }

        query = query.substring(0, query.length()-1) + ") VALUES (";

        for (int i = 0; i < fields.length; i++){
            query += "?,";
        }
        query = query.substring(0, query.length()-1) + ")";
        statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        try {
            for (int i = 1; i <= fields.length; i++) {
                fields[i-1].setAccessible(true);
                Object value = fields[i-1].get(object);
                if (value instanceof SerializedObject) {
                    statement.setInt(i,((SerializedObject) value).getId());
                } else if (value instanceof String){
                    statement.setString(i, (String) value);
                } else if (value instanceof Integer){
                    statement.setInt(i, (int)value);
                } else if (value instanceof LocalDate){
                    statement.setDate(i, java.sql.Date.valueOf((LocalDate)value));
                } else if (value instanceof LocalTime){
                    statement.setTime(i, java.sql.Time.valueOf((LocalTime)value));
                } else if (value instanceof BigDecimal){
                    statement.setBigDecimal(i, (BigDecimal)value);
                } else if (value instanceof Boolean){
                    statement.setBoolean(i, (boolean)value);
                }
                /*else if (value instanceof List){
                    for (Object element : (List)value){
                        ((SerializedObject) element).getId()
                    }
                }*/

            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

//        query = query.substring(0, query.length()-1) + ")";

        int rowsAffected = statement.executeUpdate();
        if(rowsAffected == 0){
            throw new SQLException("No rows affected");
        }
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()){
            int id = generatedKeys.getInt("id");
            return id;
        } else {
            throw new SQLException("No id obtained");
        }
    }
}
