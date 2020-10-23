package course.java.sdm.engine.engine.notifications;

import course.java.sdm.engine.engine.Location;
import course.java.sdm.engine.engine.Store;

import java.util.Objects;

public class StoreNotification {

    private final String ownerName;
    private final String storeName;
    private final String locationStr;
    private final String itemsRatio;

    public StoreNotification(Store store, int totalZoneItems) {
        this.ownerName = store.getOwnerName();
        this.storeName = store.getName();
        this.locationStr = Location.getLocationStr(store.getLocation());
        this.itemsRatio = String.format("%d/%d", store.getNumberOfItems(), totalZoneItems);
    }

    public String getOwnerName() {
        return ownerName;
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

    @Override
    public String toString() {
        return "StoreNotification{" +
                "ownerName='" + ownerName + '\'' +
                ", storeName='" + storeName + '\'' +
                ", locationStr='" + locationStr + '\'' +
                ", itemsRatio='" + itemsRatio + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StoreNotification that = (StoreNotification) o;

        if (!Objects.equals(ownerName, that.ownerName)) return false;
        if (!Objects.equals(storeName, that.storeName)) return false;
        if (!Objects.equals(locationStr, that.locationStr)) return false;
        return Objects.equals(itemsRatio, that.itemsRatio);
    }

    @Override
    public int hashCode() {
        int result = ownerName != null ? ownerName.hashCode() : 0;
        result = 31 * result + (storeName != null ? storeName.hashCode() : 0);
        result = 31 * result + (locationStr != null ? locationStr.hashCode() : 0);
        result = 31 * result + (itemsRatio != null ? itemsRatio.hashCode() : 0);
        return result;
    }
}
