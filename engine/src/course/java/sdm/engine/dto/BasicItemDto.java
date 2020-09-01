package course.java.sdm.engine.dto;
import course.java.sdm.engine.engine.Item;

public class BasicItemDto {
    private final int id;
    private final String name;
    private final String purchaseCategory;

    public BasicItemDto(Item item) {
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
