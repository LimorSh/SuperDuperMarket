package course.java.sdm.engine.engine.notifications;

import course.java.sdm.engine.engine.Store;

import java.util.ArrayList;
import java.util.List;

public class NotificationManager {

    private final List<StoreNotification> storeNotifications;

    public NotificationManager() {
        storeNotifications = new ArrayList<>();
    }

    public synchronized void addStoreNotification(Store store, int totalZoneItems) {
        storeNotifications.add(new StoreNotification(store, totalZoneItems));
    }

    public synchronized List<StoreNotification> getStoreNotifications(int fromIndex){
        if (fromIndex < 0 || fromIndex > storeNotifications.size()) {
            fromIndex = 0;
        }
        return storeNotifications.subList(fromIndex, storeNotifications.size());
    }

    public int getVersion() {
        return storeNotifications.size();
    }
}
