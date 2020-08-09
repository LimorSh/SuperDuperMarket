package course.java.sdm.engine;

import course.java.sdm.engine.jaxb.schema.generated.SDMItem;

public class Item {

    public enum PurchaseType {
        PER_WEIGHT("per weight"),
        PER_UNIT("per unit"),
        ;

        private final String purchaseTypeStr;

        PurchaseType(String purchaseType) {
            this.purchaseTypeStr = purchaseType;
        }
    }

    private final int id;
    private final String name;
    private final PurchaseType purchaseType;

    public Item(int id, String name, PurchaseType purchaseType) {
        this.id = id;
        this.name = name;
        this.purchaseType = purchaseType;
    }

    public Item(Item item) {
        this.id = item.id;
        this.name = item.name;
        this.purchaseType = item.purchaseType;
    }

    public Item(SDMItem sdmItem) {
        this.id = sdmItem.getId();
        this.name = sdmItem.getName().toLowerCase();
        this.purchaseType = convertStringToPurchaseType(sdmItem.getPurchaseCategory());
    }

    private PurchaseType convertStringToPurchaseType(String purchaseCategory) {
        if (purchaseCategory.toLowerCase().contains("quantity")) {
            return PurchaseType.PER_UNIT;
        }
        return PurchaseType.PER_WEIGHT;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PurchaseType getPurchaseType() {
        return purchaseType;
    }

    @Override
    public String toString() {
        return "{ID: " + id +
                ", Name: '" + name + '\'' +
                ", Purchase Type: " + purchaseType.purchaseTypeStr
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return id == item.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
