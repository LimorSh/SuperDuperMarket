package course.java.sdm.javafx.components.sdmData.locationMap;

import course.java.sdm.engine.dto.CustomerDto;
import course.java.sdm.engine.dto.StoreDto;

import java.util.ArrayList;
import java.util.Collection;

public class LocationMapData {

    protected Collection<StoreDto> stores;
    protected Collection<CustomerDto> customers;
    protected int minLocation;
    protected int maxLocation;

    public void setValuesData(Collection<StoreDto> stores, Collection<CustomerDto> customers,
                              ArrayList<Integer> minAndMaxLocations) {
        this.stores = stores;
        this.customers = customers;
        this.minLocation = minAndMaxLocations.get(0);
        this.maxLocation = minAndMaxLocations.get(1);
    }
}
