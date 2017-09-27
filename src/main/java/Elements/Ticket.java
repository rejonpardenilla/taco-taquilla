package Elements;

import java.math.BigDecimal;

public class Ticket {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Seating getSeating() {
        return seating;
    }

    public void setSeating(Seating seating) {
        this.seating = seating;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    private int id;
    private Seating seating;
    private BigDecimal price;
    private boolean returned;

}
