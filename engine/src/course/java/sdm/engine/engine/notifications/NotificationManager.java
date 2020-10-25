package course.java.sdm.engine.engine.notifications;

import course.java.sdm.engine.engine.Store;
import course.java.sdm.engine.engine.StoreOrder;

import java.util.ArrayList;
import java.util.List;

public class NotificationManager {

    private final List<StoreNotification> storeNotifications;
    private final List<StoreFeedbackNotification> storeFeedbackNotifications;
    private final List<OrderNotification> orderNotifications;

    public NotificationManager() {
        storeNotifications = new ArrayList<>();
        storeFeedbackNotifications = new ArrayList<>();
        orderNotifications = new ArrayList<>();
    }

    // StoreNotifications
    public synchronized void addStoreNotification(String zoneName, String zoneOwnerName,
                                                  Store store, int totalZoneItems) {
        storeNotifications.add(new StoreNotification(zoneName, zoneOwnerName, store, totalZoneItems));
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

    // StoreFeedbackNotifications
    public synchronized void addStoreFeedbackNotification(
            String zoneName, String storeOwnerName,
            String storeName, String customerName, int rate) {
        storeFeedbackNotifications.add(new StoreFeedbackNotification(
                zoneName, storeOwnerName, storeName, customerName, rate));
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

    // OrderNotifications
    public synchronized void addOrderNotification(String zoneName, StoreOrder storeOrder) {
        orderNotifications.add(new OrderNotification(zoneName, storeOrder));
    }

    public synchronized List<OrderNotification> getOrderNotifications(int fromIndex){
        if (fromIndex < 0 || fromIndex > orderNotifications.size()) {
            fromIndex = 0;
        }
        return orderNotifications.subList(fromIndex, orderNotifications.size());
    }

    public int getOrderNotificationsVersion() {
        return orderNotifications.size();
    }
}
