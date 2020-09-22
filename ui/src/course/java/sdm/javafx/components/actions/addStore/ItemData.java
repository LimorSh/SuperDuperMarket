package course.java.sdm.javafx.components.actions.addStore;

import course.java.sdm.engine.dto.BasicItemDto;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ItemData {

    protected SimpleIntegerProperty id;
    protected SimpleStringProperty name;
    protected SimpleStringProperty purchaseCategory;

    public ItemData(BasicItemDto basicItemDto) {
        this.id = new SimpleIntegerProperty(basicItemDto.getId());
        this.name = new SimpleStringProperty(basicItemDto.getName());
        this.purchaseCategory = new SimpleStringProperty(basicItemDto.getPurchaseCategory());
    }

    public int getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public String getPurchaseCategory() {
        return purchaseCategory.get();
    }
}
