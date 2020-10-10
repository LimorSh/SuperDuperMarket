package course.java.sdm.engine.engine;
import course.java.sdm.engine.Constants;
import course.java.sdm.engine.jaxb.schema.generated.SDMItem;

public class Item {

    public enum PurchaseCategory {
        PER_UNIT(Constants.ITEM_PURCHASE_CATEGORY_PER_UNIT_STR),
        PER_WEIGHT(Constants.ITEM_PURCHASE_CATEGORY_PER_WEIGHT_STR),
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
        this.name = name.trim();
        this.purchaseCategory = purchaseCategory;
    }

    public Item(Item item) {
        this(item.id, item.name, item.purchaseCategory);
    }

    public Item(SDMItem sdmItem) {
        this(sdmItem.getId(), sdmItem.getName(), convertStringToPurchaseCategory(sdmItem.getPurchaseCategory()));
    }

    public static PurchaseCategory convertStringToPurchaseCategory(String purchaseCategory) {
        if (purchaseCategory.toLowerCase().contains(Constants.ITEM_PURCHASE_CATEGORY_PER_UNIT_STR)) {
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
