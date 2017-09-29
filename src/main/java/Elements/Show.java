package Elements;

import Elements.Methods.SerializedObject;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Show extends SerializedObject {
    LocalDate date;
    LocalTime time;
    Play play;
    BigDecimal price;
    boolean cancelled;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Play getPlay() {
        return play;
    }

    public void setPlay(Play play) {
        this.play = play;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public String toString() {
        return play.getName() + ": " + time;
    }
}
