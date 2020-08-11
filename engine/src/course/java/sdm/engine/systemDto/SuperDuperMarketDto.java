package course.java.sdm.engine.systemDto;

import course.java.sdm.engine.Configurations;
import course.java.sdm.engine.Store;
import java.util.ArrayList;
import java.util.Collection;


public class SuperDuperMarketDto {

    public static Collection<StoreDto> getStoresDto(Collection<Store> stores) {
        Collection<StoreDto> storesDto = new ArrayList<>();
        for (Store store : stores) {
            storesDto.add(new StoreDto(store));
        }
        return storesDto;
    }


    public static String getItemPurchaseTypePerUnitStr() {
        return Configurations.ITEM_PURCHASE_TYPE_PER_UNIT_STR;
    }

    public static String getItemPurchaseTypePerWeightStr() {
        return Configurations.ITEM_PURCHASE_TYPE_PER_WEIGHT_STR;
    }
}
