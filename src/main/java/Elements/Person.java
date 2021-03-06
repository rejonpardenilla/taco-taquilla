package Elements;

import DataAccess.Implementations.PersonDao;
import Elements.Base.DataAccessMethods;
import Elements.Base.SerializedObject;

import java.sql.SQLException;

public class Person extends SerializedObject implements DataAccessMethods {
    private String name;
    private String lastName;
    private String phone;
    private String phoneAlt;
    private String email;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneAlt() {
        return phoneAlt;
    }

    public void setPhoneAlt(String phoneAlt) {
        this.phoneAlt = phoneAlt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public Person save() throws SQLException {
        PersonDao personDao = new PersonDao();
        this.id = personDao.insertPerson(this);
        return this;
    }
}
