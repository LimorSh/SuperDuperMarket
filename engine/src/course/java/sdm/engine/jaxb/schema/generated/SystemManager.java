package course.java.sdm.engine.jaxb.schema.generated;

import course.java.sdm.engine.DataLoader;
import course.java.sdm.engine.SuperDuperMarket;

public class SystemManager {

    SuperDuperMarket superDuperMarket;

    public void loadSystemData(String dataPath) {
        superDuperMarket = DataLoader.loadFromXmlFile(dataPath);
    }

//    public Set<Store>
}
