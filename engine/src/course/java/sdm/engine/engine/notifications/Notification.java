package course.java.sdm.engine.engine.notifications;

import course.java.sdm.engine.Utils;

import java.util.Date;
import java.util.Objects;

public abstract class Notification {

    protected final String ownerName;
    protected final String type;
    protected final String dateStr;

    public Notification(String ownerName, String type) {
        this.ownerName = ownerName;
        this.type = type;
        this.dateStr = Utils.convertDateToString(new Date());
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getType() {
        return type;
    }

    public String getDateStr() {
        return dateStr;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "ownerName='" + ownerName + '\'' +
                ", type='" + type + '\'' +
                ", dateStr='" + dateStr + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Notification that = (Notification) o;

        if (!Objects.equals(ownerName, that.ownerName)) return false;
        if (!Objects.equals(type, that.type)) return false;
        return Objects.equals(dateStr, that.dateStr);
    }

    @Override
    public int hashCode() {
        int result = ownerName != null ? ownerName.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (dateStr != null ? dateStr.hashCode() : 0);
        return result;
    }
}
