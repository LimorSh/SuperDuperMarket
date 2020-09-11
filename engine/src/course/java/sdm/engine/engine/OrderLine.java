package course.java.sdm.engine.engine;

import java.util.Objects;

public class OrderLine {

    private final Item item;
    private float quantity;
    private final float cost;   //the price of one item/kg in the order
    private final float totalCost;
//    private final boolean discount = false;

    public OrderLine(Item item, float quantity, float cost) {
        this.item = item;
        this.quantity = quantity;
        this.cost = cost;
        this.totalCost = quantity * cost;
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

    public float getTotalCost() {
        return totalCost;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public int calcTotalItems() {
        int totalItems;
        if (item.getPurchaseCategory().getPurchaseCategoryStr()
                .equals(Item.PurchaseCategory.PER_UNIT.getPurchaseCategoryStr())) {
            totalItems = (int) quantity;
        }
        else {
            totalItems = 1;
        }
        return totalItems;
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
