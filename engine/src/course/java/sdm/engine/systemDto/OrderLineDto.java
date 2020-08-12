package course.java.sdm.engine.systemDto;

public class OrderLineDto {
    private final int itemId;
    private final float quantity;
    //    private final float cost;

    public OrderLineDto(int itemId, float quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }

//    public float getCost() {
//        return cost;
//    }

    public float getQuantity() {
        return quantity;
    }

    public int getItem() {
        return itemId;
    }
}
