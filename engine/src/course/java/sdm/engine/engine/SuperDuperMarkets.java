package course.java.sdm.engine.engine;

import java.util.HashMap;
import java.util.Map;

public class SuperDuperMarkets {
    private final Map<String, SuperDuperMarket> superDuperMarkets;    // the key is zone name

    public SuperDuperMarkets() {
        superDuperMarkets = new HashMap<>();
    }

    public synchronized void addSuperDuperMarket(SuperDuperMarket superDuperMarket) {
        String zoneName = superDuperMarket.getZoneName();
        if (!isZoneNameExists(zoneName)) {
            superDuperMarkets.put(zoneName, superDuperMarket);
        }
        else {
            throw new IllegalArgumentException("Zone name " + zoneName + "already exists. Zone name must be unique.");
        }
    }

    public boolean isZoneNameExists(String name) {
        for (String zoneName : superDuperMarkets.keySet()) {
            if (zoneName.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}
