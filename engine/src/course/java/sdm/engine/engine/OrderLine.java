package course.java.sdm.engine.engine;

import java.util.Objects;

public class OrderLine {
    private final Item item;
    private float quantity;
    private final float cost;
//    private final boolean discount = false;

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

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "item=" + item +
                ", quantity=" + quantity +
                ", cost=" + cost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderLine orderLine = (OrderLine) o;

        return Objects.equals(item, orderLine.item);
    }

    @Override
    public int hashCode() {
        return item != null ? item.hashCode() : 0;
    }
}
