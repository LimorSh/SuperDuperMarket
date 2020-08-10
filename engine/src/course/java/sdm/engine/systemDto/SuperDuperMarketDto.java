package course.java.sdm.engine.systemDto;

import course.java.sdm.engine.Store;
import course.java.sdm.engine.systemDto.StoreDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SuperDuperMarketDto {

    public static Collection<StoreDto> getStoresDto(Collection<Store> stores) {
        Collection<StoreDto> storesDto = new ArrayList<>();
        for (Store store : stores) {
            storesDto.add(new StoreDto(store));
        }
        return storesDto;
    }



}
