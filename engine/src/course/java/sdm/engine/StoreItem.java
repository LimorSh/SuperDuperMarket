package course.java.sdm.engine;

import course.java.sdm.engine.jaxb.schema.generated.SDMItem;

public class StoreItem extends Item{

    private float price;
    private int totalNumberSold;

    public StoreItem(int id, String name, PurchaseType purchaseType, float price) {
        super(id, name, purchaseType);
        this.price = price;
    }

    public StoreItem(Item item, float price) {
        super(item);
        this.price = price;
    }

    public StoreItem(SDMItem sdmItem, float price) {
        super(sdmItem);
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getTotalNumberSold() {
        return totalNumberSold;
    }

    public void updateTotalNumberSold(int quantity) {
        this.totalNumberSold += quantity;
    }

    @Override
    public String toString() {
        return ", Price: " + price +
                ", Quantity: " + totalNumberSold +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        StoreItem storeItem = (StoreItem) o;

        if (Float.compare(storeItem.price, price) != 0) return false;
        return totalNumberSold == storeItem.totalNumberSold;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + totalNumberSold;
        return result;
    }
}
