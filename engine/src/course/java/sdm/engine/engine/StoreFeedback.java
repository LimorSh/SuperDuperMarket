package course.java.sdm.engine.engine;

import java.util.Date;

public class StoreFeedback {

    private final String customerName;
    private final Date orderDate;
    private final int rate;
    private final String description;

    public StoreFeedback(String customerName, Date orderDate, int rate, String description) {
        this.customerName = customerName;
        this.orderDate = orderDate;
        this.rate = rate;
        this.description = description;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public int getRate() {
        return rate;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "StoreFeedback{" +
                "customerName='" + customerName + '\'' +
                ", orderDate=" + orderDate +
                ", rate=" + rate +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StoreFeedback that = (StoreFeedback) o;

        if (rate != that.rate) return false;
        if (customerName != null ? !customerName.equals(that.customerName) : that.customerName != null) return false;
        if (orderDate != null ? !orderDate.equals(that.orderDate) : that.orderDate != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = customerName != null ? customerName.hashCode() : 0;
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + rate;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
