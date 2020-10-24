package course.java.sdm.engine.engine.notifications;

import java.util.Objects;

public abstract class Notification {

    protected final String zoneOwnerName;
    protected final String storeOwnerName;
    protected final String type;

    public Notification(String zoneOwnerName, String storeOwnerName, String type) {
        this.zoneOwnerName = zoneOwnerName;
        this.storeOwnerName = storeOwnerName;
        this.type = type;
    }

    public String getZoneOwnerName() {
        return zoneOwnerName;
    }

    public String getStoreOwnerName() {
        return storeOwnerName;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "zoneOwnerName='" + zoneOwnerName + '\'' +
                ", storeOwnerName='" + storeOwnerName + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Notification that = (Notification) o;

        if (!Objects.equals(zoneOwnerName, that.zoneOwnerName))
            return false;
        if (!Objects.equals(storeOwnerName, that.storeOwnerName))
            return false;
        return Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        int result = zoneOwnerName != null ? zoneOwnerName.hashCode() : 0;
        result = 31 * result + (storeOwnerName != null ? storeOwnerName.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
