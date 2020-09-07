package course.java.sdm.javafx.components.actions.order.summery;

import course.java.sdm.javafx.SuperDuperMarketConstants;
import javafx.beans.property.SimpleStringProperty;

public class OrderSummeryData {

    protected SimpleStringProperty customerDetails;

    public OrderSummeryData() {
        customerDetails = new SimpleStringProperty(SuperDuperMarketConstants.INIT_STRING);
    }

    public void setDataValues(OrderSummeryInfo orderSummeryInfo) {
        customerDetails.set(String.format("ID %d | %s | Location: (%d,%d)", orderSummeryInfo.getCustomerId(),
                orderSummeryInfo.getCustomerName(), orderSummeryInfo.getCustomerXLocation(),
                orderSummeryInfo.getCustomerYLocation()));




    }
}
