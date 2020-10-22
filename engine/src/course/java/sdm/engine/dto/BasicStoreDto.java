package course.java.sdm.engine.dto;
import course.java.sdm.engine.engine.Location;
import course.java.sdm.engine.engine.Store;

public class BasicStoreDto {
    private final int id;
    private final String name;
    private final String location;
    private final int ppk;

    public BasicStoreDto(Store store) {
        this.id = store.getId();
        this.name = store.getName();
        int xLocation = store.getLocation().getCoordinate().x;
        int yLocation = store.getLocation().getCoordinate().y;
        this.location = Location.getLocationStr(xLocation, yLocation);
        this.ppk = store.getPpk();
    }

    public BasicStoreDto(StoreDto storeDto) {
        this.id = storeDto.getId();
        this.name = storeDto.getName();
        int xLocation = storeDto.getXLocation();
        int yLocation = storeDto.getYLocation();
        this.location = Location.getLocationStr(xLocation, yLocation);
        this.ppk = storeDto.getPpk();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getPpk() {
        return ppk;
    }
}
