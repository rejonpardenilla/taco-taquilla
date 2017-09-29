package Elements;

import Elements.Methods.SerializedObject;

public class Seat extends SerializedObject{
    private String row;
    private int number;
    private Zone zone;

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

}
