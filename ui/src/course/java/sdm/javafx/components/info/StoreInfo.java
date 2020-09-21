package course.java.sdm.javafx.components.info;

import course.java.sdm.engine.dto.StoreDto;

public class StoreInfo {
    private final int id;
    private final String name;
    private final int xLocation;
    private final int yLocation;

    public StoreInfo(StoreDto storeDto) {
        this.id = storeDto.getId();
        this.name = storeDto.getName();
        this.xLocation = storeDto.getXLocation();
        this.yLocation = storeDto.getYLocation();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getXLocation() {
        return xLocation;
    }

    public int getYLocation() {
        return yLocation;
    }

    @Override
    public String toString() {
        return String.format("ID %d: %s (%d,%d)", id, name, xLocation, yLocation);
    }
}
