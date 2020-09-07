package course.java.sdm.javafx.components.actions.order.summery;

public class OrderSummeryInfo {

    private int customerId;
    private String customerName;
    private int customerXLocation;
    private int customerYLocation;

    public OrderSummeryInfo() {
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCustomerXLocation() {
        return customerXLocation;
    }

    public void setCustomerXLocation(int customerXLocation) {
        this.customerXLocation = customerXLocation;
    }

    public int getCustomerYLocation() {
        return customerYLocation;
    }

    public void setCustomerYLocation(int customerYLocation) {
        this.customerYLocation = customerYLocation;
    }
}
