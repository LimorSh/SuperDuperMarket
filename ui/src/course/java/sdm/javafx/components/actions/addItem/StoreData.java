package course.java.sdm.javafx.components.actions.addItem;

import course.java.sdm.engine.dto.StoreDto;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class StoreData {

    protected SimpleIntegerProperty id;
    protected SimpleStringProperty name;
    protected SimpleIntegerProperty ppk;
    protected SimpleStringProperty location;

    public StoreData(StoreDto storeDto) {
        this.id = new SimpleIntegerProperty(storeDto.getId());
        this.name = new SimpleStringProperty(storeDto.getName());
        this.ppk = new SimpleIntegerProperty(storeDto.getPpk());
        int locationX = storeDto.getXLocation();
        int locationY = storeDto.getYLocation();
        this.location = new SimpleStringProperty(getStrLocation(locationX, locationY));
    }

    private String getStrLocation(int x, int y) {
        return String.format("(%d, %d)", x, y);
    }

    public int getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public int getPpk() {
        return ppk.get();
    }

    public String getLocation() {
        return location.get();
    }

}
