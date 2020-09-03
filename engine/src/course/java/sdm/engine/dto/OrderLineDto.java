package course.java.sdm.engine.dto;

public class OrderLineDto {
    private final int itemId;
    private final float quantity;

    public OrderLineDto(int itemId, float quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public float getQuantity() {
        return quantity;
    }

    public int getItem() {
        return itemId;
    }
}
