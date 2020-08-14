package course.java.sdm.engine;

import course.java.sdm.engine.jaxb.schema.generated.SDMItem;

public class Item {

    public enum PurchaseCategory {
        PER_UNIT(Configurations.ITEM_PURCHASE_CATEGORY_PER_UNIT_STR),
        PER_WEIGHT(Configurations.ITEM_PURCHASE_CATEGORY_PER_WEIGHT_STR),
        ;

        private final String purchaseCategoryStr;

        PurchaseCategory(String purchaseCategory) {
            this.purchaseCategoryStr = purchaseCategory;
        }

        public String getPurchaseCategoryStr() {
            return purchaseCategoryStr;
        }
    }

    private final int id;
    private final String name;
    private final PurchaseCategory purchaseCategory;

    public Item(int id, String name, PurchaseCategory purchaseCategory) {
        this.id = id;
        this.name = name;
        this.purchaseCategory = purchaseCategory;
    }

    public Item(Item item) {
        this.id = item.id;
        this.name = item.name;
        this.purchaseCategory = item.purchaseCategory;
    }

    public Item(SDMItem sdmItem) {
        this.id = sdmItem.getId();
        this.name = sdmItem.getName().toLowerCase();
        this.purchaseCategory = convertStringToPurchaseCategory(sdmItem.getPurchaseCategory());
    }

    private PurchaseCategory convertStringToPurchaseCategory(String purchaseCategory) {
        if (purchaseCategory.toLowerCase().contains(Configurations.ITEM_PURCHASE_CATEGORY_PER_UNIT_STR)) {
            return PurchaseCategory.PER_UNIT;
        }
        return PurchaseCategory.PER_WEIGHT;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PurchaseCategory getPurchaseCategory() {
        return purchaseCategory;
    }

    @Override
    public String toString() {
        return "{ID: " + id +
                ", Name: '" + name + '\'' +
                ", Purchase Category: " + purchaseCategory.purchaseCategoryStr
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
