package DataAccess.Interfaces;

import Elements.Base.SerializedObject;
import Elements.Person;

import java.sql.SQLException;
import java.util.List;

public interface PersonDaoInterface extends BaseDao{
    public int insertPerson(Person person) throws SQLException;
    public boolean updatePerson(Person person);
    public boolean deletePersonById(int id);
}
