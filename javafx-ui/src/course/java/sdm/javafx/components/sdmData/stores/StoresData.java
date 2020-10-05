package course.java.sdm.javafx.components.sdmData.stores;

import course.java.sdm.engine.dto.StoreDto;

import java.util.Collection;

public class StoresData {

    protected Collection<StoreDto> storesDto;

    public void setStoresDto(Collection<StoreDto> storesDto) {
        this.storesDto = storesDto;
    }

    protected StoreDto getStoreDto(int storeId) {
        for (StoreDto storeDto : storesDto) {
            if (storeDto.getId() == storeId)
                return storeDto;
        }
        return null;
    }
}
