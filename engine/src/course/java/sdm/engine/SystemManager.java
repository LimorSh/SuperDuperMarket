package course.java.sdm.engine;

import course.java.sdm.engine.DataLoader;
import course.java.sdm.engine.SuperDuperMarket;

import java.util.Map;

public class SystemManager {

    static SuperDuperMarket superDuperMarket;

    public static void loadSystemData(String dataPath) {
        superDuperMarket = DataLoader.loadFromXmlFile(dataPath);
    }

    public static Map<Integer, Store> getStores() {
        return superDuperMarket.getStores();
    }
}
