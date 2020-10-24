package course.java.sdm.engine.engine.notifications;

import course.java.sdm.engine.Constants;

import java.util.Objects;

public class StoreFeedbackNotification extends Notification {

    private final String storeName;
    private final String customerName;
    private final int rate;

    public StoreFeedbackNotification(String ownerName, String storeName, String customerName, int rate) {
        super(ownerName, Constants.NOTIFICATION_TYPE_NEW_STORE_FEEDBACK_STR);
        this.storeName = storeName;
        this.customerName = customerName;
        this.rate = rate;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return "StoreFeedbackNotification{" +
                "storeName='" + storeName + '\'' +
                ", customerName='" + customerName + '\'' +
                ", rate=" + rate +
                ", ownerName='" + ownerName + '\'' +
                ", type='" + type + '\'' +
                ", dateStr='" + dateStr + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StoreFeedbackNotification that = (StoreFeedbackNotification) o;

        if (rate != that.rate) return false;
        if (!Objects.equals(storeName, that.storeName)) return false;
        return Objects.equals(customerName, that.customerName);
    }

    @Override
    public int hashCode() {
        int result = storeName != null ? storeName.hashCode() : 0;
        result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
        result = 31 * result + rate;
        return result;
    }
}
