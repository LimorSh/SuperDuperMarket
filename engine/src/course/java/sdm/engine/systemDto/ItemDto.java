package course.java.sdm.engine.systemDto;

import course.java.sdm.engine.Item;

public class ItemDto {
    private final int id;
    private final String name;
    private final Item.PurchaseType purchaseType;

    public ItemDto(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.purchaseType = item.getPurchaseType();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Item.PurchaseType getPurchaseType() {
        return purchaseType;
    }
}
