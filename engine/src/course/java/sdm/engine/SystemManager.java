package course.java.sdm.engine;

import course.java.sdm.engine.systemDto.StoreDto;
import course.java.sdm.engine.systemDto.SuperDuperMarketDto;

import java.util.Collection;

public class SystemManager {

    static SuperDuperMarket superDuperMarket;

    public static void loadSystemData(String dataPath) {
        superDuperMarket = DataLoader.loadFromXmlFile(dataPath);
    }

//    public static Map<Integer, Store> getStores() {
//        return superDuperMarket.getStores();
//    }

    public static Collection<StoreDto> getStoresDto() {
        return SuperDuperMarketDto.getStoresDto(superDuperMarket.getStores());
    }
}
