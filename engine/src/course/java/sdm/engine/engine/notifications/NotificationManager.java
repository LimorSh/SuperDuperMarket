package course.java.sdm.engine.engine.notifications;

import course.java.sdm.engine.engine.Store;

import java.util.ArrayList;
import java.util.List;

public class NotificationManager {

    private final List<StoreNotification> storeNotifications;
    private final List<StoreFeedbackNotification> storeFeedbackNotifications;

    public NotificationManager() {
        storeNotifications = new ArrayList<>();
        storeFeedbackNotifications = new ArrayList<>();
    }

    public synchronized void addStoreNotification(String zoneOwnerName, Store store, int totalZoneItems) {
        storeNotifications.add(new StoreNotification(zoneOwnerName, store, totalZoneItems));
    }

    public synchronized void addStoreFeedbackNotification(
            String zoneOwnerName, String storeOwnerName,
            String storeName, String customerName, int rate) {
        storeFeedbackNotifications.add(new StoreFeedbackNotification(
                zoneOwnerName, storeOwnerName, storeName, customerName, rate));
    }

    public synchronized List<StoreNotification> getStoreNotifications(int fromIndex){
        if (fromIndex < 0 || fromIndex > storeNotifications.size()) {
            fromIndex = 0;
        }
        return storeNotifications.subList(fromIndex, storeNotifications.size());
    }

    public int getStoreNotificationsVersion() {
        return storeNotifications.size();
    }

    public synchronized List<StoreFeedbackNotification> getStoreFeedbackNotifications(int fromIndex){
        if (fromIndex < 0 || fromIndex > storeFeedbackNotifications.size()) {
            fromIndex = 0;
        }
        return storeFeedbackNotifications.subList(fromIndex, storeFeedbackNotifications.size());
    }

    public int getStoreFeedbackNotificationsVersion() {
        return storeFeedbackNotifications.size();
    }


}
