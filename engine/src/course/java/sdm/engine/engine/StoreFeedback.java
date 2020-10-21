package course.java.sdm.engine.engine;

import java.util.Date;
import java.util.Objects;

public class StoreFeedback {

    private final int storeId;
    private final String storeName;
    private final Date orderDate;
    private final String customerName;
    private final int rate;
    private final String feedback;

    public StoreFeedback(int storeId, String storeName, Date orderDate, String customerName,
                         int rate, String feedback) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.rate = rate;
        this.feedback = feedback;
    }

    public int getStoreId() {
        return storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getRate() {
        return rate;
    }

    public String getFeedback() {
        return feedback;
    }

    @Override
    public String toString() {
        return "StoreFeedback{" +
                "storeId=" + storeId +
                ", storeName='" + storeName + '\'' +
                ", orderDate=" + orderDate +
                ", customerName='" + customerName + '\'' +
                ", rate=" + rate +
                ", feedback='" + feedback + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StoreFeedback that = (StoreFeedback) o;

        if (storeId != that.storeId) return false;
        if (rate != that.rate) return false;
        if (!Objects.equals(storeName, that.storeName)) return false;
        if (!Objects.equals(orderDate, that.orderDate)) return false;
        if (!Objects.equals(customerName, that.customerName)) return false;
        return Objects.equals(feedback, that.feedback);
    }

    @Override
    public int hashCode() {
        int result = storeId;
        result = 31 * result + (storeName != null ? storeName.hashCode() : 0);
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
        result = 31 * result + rate;
        result = 31 * result + (feedback != null ? feedback.hashCode() : 0);
        return result;
    }
}
