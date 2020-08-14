package course.java.sdm.engine.systemDto;

import course.java.sdm.engine.Item;

public class ItemDto {
    private final int id;
    private final String name;
    private final String purchaseCategory;

    public ItemDto(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.purchaseCategory = item.getPurchaseCategory().getPurchaseCategoryStr();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPurchaseCategory() {
        return purchaseCategory;
    }
}
