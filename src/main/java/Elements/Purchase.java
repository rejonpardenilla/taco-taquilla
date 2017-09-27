package Elements;

import java.math.BigDecimal;
import java.util.List;

public class Purchase {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getClient() {
        return client;
    }

    public void setClient(Person client) {
        this.client = client;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    private int id;
    private Person client;
    private BigDecimal total;

}
