package Elements;

import Elements.Base.SerializedObject;

public class Zone extends SerializedObject {
    private int discountPercent;

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

}
