package course.java.sdm.engine;

//OrderLine is per item
public class OrderLine {
    private final Item item;
    private float quantity;
//    private final float cost;

//    public OrderLine(int itemId, float quantity, float cost) {
//        this.itemId = itemId;
//        this.quantity = quantity;
//        this.cost = cost;
//    }

    public OrderLine(Item item, float quantity) {
        this.item = item;
        this.quantity = quantity;
    }


//    public float getCost() {
//        return cost;
//    }

    public float getQuantity() {
        return quantity;
    }

    public Item getItem() {
        return item;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

}
