package course.java.sdm.engine.engine.notifications;

import course.java.sdm.engine.Constants;
import course.java.sdm.engine.engine.Location;
import course.java.sdm.engine.engine.Store;

import java.util.Objects;

public class StoreNotification extends Notification {

    private final String storeName;
    private final String locationStr;
    private final String itemsRatio;

    public StoreNotification(String zoneOwnerName, Store store, int totalZoneItems) {
        super(zoneOwnerName, store.getOwnerName(), Constants.NOTIFICATION_TYPE_NEW_STORE_STR);
        this.storeName = store.getName();
        this.locationStr = Location.getLocationStr(store.getLocation());
        this.itemsRatio = String.format("%d/%d", store.getNumberOfItems(), totalZoneItems);
    }

    public String getStoreName() {
        return storeName;
    }

    public String getLocationStr() {
        return locationStr;
    }

    public String getItemsRatio() {
        return itemsRatio;
    }

    public boolean isUserZoneOwnerAndNotStoreOwner(String userName) {
        return userName.equalsIgnoreCase(zoneOwnerName) && !userName.equalsIgnoreCase(storeOwnerName);
    }

    @Override
    public String toString() {
        return "StoreNotification{" +
                "storeName='" + storeName + '\'' +
                ", locationStr='" + locationStr + '\'' +
                ", itemsRatio='" + itemsRatio + '\'' +
                ", ownerName='" + storeOwnerName + '\'' +
                ", type='" + type + '\'' +
                ", dateStr='" + dateStr + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        StoreNotification that = (StoreNotification) o;

        if (!Objects.equals(storeName, that.storeName)) return false;
        if (!Objects.equals(locationStr, that.locationStr)) return false;
        return Objects.equals(itemsRatio, that.itemsRatio);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (storeName != null ? storeName.hashCode() : 0);
        result = 31 * result + (locationStr != null ? locationStr.hashCode() : 0);
        result = 31 * result + (itemsRatio != null ? itemsRatio.hashCode() : 0);
        return result;
    }
}
