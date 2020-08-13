package course.java.sdm.engine;

public class OrderLine {
    private final Item item;
    private final float quantity;
    private final float cost;

    public OrderLine(Item item, float quantity, float cost) {
        this.item = item;
        this.quantity = quantity;
        this.cost = cost;
    }

    public float getQuantity() {
        return quantity;
    }

    public Item getItem() {
        return item;
    }

    public float getCost() {
        return cost;
    }
}
