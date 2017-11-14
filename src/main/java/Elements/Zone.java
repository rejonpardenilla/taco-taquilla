package Elements;

import DataAccess.Implementations.ZoneDao;
import Elements.Base.SerializedObject;

import java.sql.SQLException;

public class Zone extends SerializedObject {
    private int discountPercent;
    private String name;

    public int getDiscountPercent() {
        return discountPercent;
    }

    public String getName() { return name; }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public void setName(String name) { this.name = name; }

    public Zone save() throws SQLException {
        ZoneDao zoneDao = new ZoneDao();
        this.id = zoneDao.insertZone(this);
        return this;
    }
}
